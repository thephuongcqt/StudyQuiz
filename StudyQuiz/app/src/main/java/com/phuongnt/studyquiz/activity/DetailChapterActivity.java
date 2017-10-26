package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.service.MyProgressBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChapterActivity extends AppCompatActivity {
    private static final int[] numberQuestions = {10, 15, 20, 25, 30};
    public static final String[] options = {"10", "15", "20", "25", "30"};
    public static final String KEY_CHAPTER_OBJ = "currentChapter";
    private SearchChapterResponse chapter = null;
    private TextView tvSubjectTitle = null;
    private TextView tvChapterTitle = null;
    private Spinner spinnerNumber = null;
    private ArrayAdapter<String> adapter = null;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            chapter = (SearchChapterResponse) bundle.get(KEY_CHAPTER_OBJ);
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Detail Chapter");

        tvSubjectTitle = (TextView) findViewById(R.id.tv_subject_title);
        tvChapterTitle = (TextView) findViewById(R.id.tv_chapter_title);
        spinnerNumber = (Spinner) findViewById(R.id.spinner_number_question);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options);
        spinnerNumber.setAdapter(adapter);
        tvSubjectTitle.setText(chapter.getSubject() == null ? "" : chapter.getSubject().getName());
        tvChapterTitle.setText(chapter.getName());
    }

    public void onButtonStudyCardSelected(View v){
        Toast.makeText(this, "Not implement yet", Toast.LENGTH_SHORT).show();
    }

    public void onButtonStartTestSelected(View v){
        int index = spinnerNumber.getSelectedItemPosition();
        int number = numberQuestions[index];
        User user = User.getCurrentUser();
        if(user == null){
            return;
        }

        MyProgressBar.show(this);

        Map<String, String> params = new HashMap<>();
        params.put(RequestParam.CHAPTER_CHAPTERID, chapter.getChapterId() + "");
        params.put(RequestParam.CHAPTER_NUMBER, number + "");
        params.put(RequestParam.CHAPTER_USERID, user.getUserId() + "");

        IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
        Call<CommonResponse<List<QuestionResponse>>> call = iapiHelper.getChapterTest(params);
        call.enqueue(new Callback<CommonResponse<List<QuestionResponse>>>() {
            @Override
            public void onResponse(Call<CommonResponse<List<QuestionResponse>>> call, Response<CommonResponse<List<QuestionResponse>>> response) {
                if(response.isSuccessful()){
                    CommonResponse<List<QuestionResponse>> commonResponse = response.body();
                    if(commonResponse.isSuccess()){
                        onSuccess(commonResponse.getValue());
                    } else{
                        onError(commonResponse.getError());
                    }
                } else{
                    onError(AppConst.ERROR_CONNECTION);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<List<QuestionResponse>>> call, Throwable t) {
                onError(t.getMessage());
            }
        });
    }

    private void onSuccess(List<QuestionResponse> questions){
        MyProgressBar.dismiss();
        if(questions == null || questions.isEmpty()){
            return;
        }
        TestData.setQuestions(new ArrayList<Question>());
        for(QuestionResponse item : questions){
            TestData.addQuestion(item);
        }
        Intent intent = new Intent(this, TestRoomActivity.class);
        startActivity(intent);
//        Toast.makeText(this, "Success with: " + questions.size() + " items", Toast.LENGTH_SHORT).show();
    }

    private void onError(String error){
        MyProgressBar.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
