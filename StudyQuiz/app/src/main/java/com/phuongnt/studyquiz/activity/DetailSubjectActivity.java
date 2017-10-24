package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.service.MyProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSubjectActivity extends AppCompatActivity {
    private static final int[] numberQuestions = {10, 15, 20, 25, 30};
    public static final String[] options = {"10", "15", "20", "25", "30"};
    public static final String KEY_SUBJECT_OBJ = "CurrentSubject";
    private SearchSubjectResponse subject = null;
    private TextView tvSubjectTitle = null;
    private Spinner spinnerNumber = null;
    private ArrayAdapter<String> spinnerAdapter = null;
    private ArrayAdapter<String> listViewAdapter = null;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ListView lvChapters;
    private TextView tvChapterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            subject = (SearchSubjectResponse) bundle.get(KEY_SUBJECT_OBJ);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarTitle.setText("Detail Subject");

        lvChapters = (ListView) findViewById(R.id.lv_chapter_in_subject);
        tvSubjectTitle = (TextView) findViewById(R.id.tv_subject_title);
        spinnerNumber = (Spinner) findViewById(R.id.spinner_number_question);
        tvChapterTitle = (TextView) findViewById(R.id.tv_chapter_title);
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, options);
        spinnerNumber.setAdapter(spinnerAdapter);

        List<String> chapters = new ArrayList<>();
        for (SearchChapterResponse item : subject.getChapters()){
            chapters.add(item.getName());
        }
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chapters);
        lvChapters.setAdapter(listViewAdapter);
        if(chapters.isEmpty()){
            tvChapterTitle.setText("Subject have no chapter");
        }
        tvSubjectTitle.setText(subject.getName());

        setEventListener();
    }

    private void setEventListener(){
        lvChapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchChapterResponse chapter = subject.getChapters().get(position);
                chapter.setSubject(subject);
                Intent intent = new Intent(DetailSubjectActivity.this, DetailChapterActivity.class);
                intent.putExtra(DetailChapterActivity.KEY_CHAPTER_OBJ, chapter);
                startActivity(intent);
            }
        });
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
        params.put(RequestParam.SUBJECT_SUBJECTID, subject.getSubjectId() + "");
        params.put(RequestParam.SUBJECT_NUMBER, number + "");
        params.put(RequestParam.SUBJECT_USERID, user.getUserId() + "");

        IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
        Call<CommonResponse<List<QuestionResponse>>> call = iapiHelper.getSubjectTest(params);
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
            Toast.makeText(this, "No question to test", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Success with: " + questions.size() + " items", Toast.LENGTH_SHORT).show();
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
