package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.database.ActivityDB;
import com.phuongnt.studyquiz.database.ChapterDB;
import com.phuongnt.studyquiz.database.SubjectDB;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.viewmodel.Activity;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChapterActivity extends AppCompatActivity {
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
        getComponent();
        initComponent();
    }

    private void getComponent(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        tvSubjectTitle = (TextView) findViewById(R.id.tv_subject_title);
        tvChapterTitle = (TextView) findViewById(R.id.tv_chapter_title);
        spinnerNumber = (Spinner) findViewById(R.id.spinner_number_question);
    }

    private void initComponent(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            chapter = (SearchChapterResponse) bundle.get(AppConst.KEY_CHAPTER_OBJ);
            if(chapter == null){
                onBackPressed();
                return;
            }
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText("Detail Chapter");
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, AppConst.OPTIONS);
        spinnerNumber.setAdapter(adapter);
        tvSubjectTitle.setText("Subject: " + chapter.getSubject() == null ? "" : chapter.getSubject().getName());
        tvChapterTitle.setText("Chapter: " + chapter.getName());
    }

    public void onButtonStudyCardSelected(View v){
        int index = spinnerNumber.getSelectedItemPosition();
        int number = AppConst.NUMBER_QUESTIONS[index];
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
        Call<CommonResponse<List<QuestionResponse>>> call = iapiHelper.getChapterCards(params);
        call.enqueue(new Callback<CommonResponse<List<QuestionResponse>>>() {
            @Override
            public void onResponse(Call<CommonResponse<List<QuestionResponse>>> call, Response<CommonResponse<List<QuestionResponse>>> response) {
                if(response.isSuccessful()){
                    CommonResponse<List<QuestionResponse>> commonResponse = response.body();
                    if(commonResponse.isSuccess()){
                        onSuccessCard(commonResponse.getValue());
                    } else{
                        onErrorCard(commonResponse.getError());
                    }
                } else{
                    onErrorCard(AppConst.ERROR_CONNECTION);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<List<QuestionResponse>>> call, Throwable t) {
                onErrorCard(t.getMessage());
            }
        });
    }

    private void onSuccessCard(List<QuestionResponse> questions){
        storeActivity(Activity.TYPE_FLASH_CARD);
        MyProgressBar.dismiss();
        if(questions == null || questions.isEmpty()){
            Toast.makeText(this, "No question to test", Toast.LENGTH_SHORT).show();
            return;
        }
        TestData.setQuestions(new ArrayList<Question>());
        for(QuestionResponse item : questions){
            TestData.addQuestion(item);
        }
        Intent intent = new Intent(this, FlashCardRoomActivity.class);
        intent.putExtra(AppConst.SOURCE_OBJECT_KEY, chapter);
        startActivity(intent);
    }

    private void onErrorCard(String error){
        MyProgressBar.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void onButtonStartTestSelected(View v){
        int index = spinnerNumber.getSelectedItemPosition();
        int number = AppConst.NUMBER_QUESTIONS[index];
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
                        onSuccessTest(commonResponse.getValue());
                    } else{
                        onErrorTest(commonResponse.getError());
                    }
                } else{
                    onErrorTest(AppConst.ERROR_CONNECTION);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse<List<QuestionResponse>>> call, Throwable t) {
                onErrorTest(t.getMessage());
            }
        });
    }

    private void onSuccessTest(List<QuestionResponse> questions){
        storeActivity(Activity.TYPE_TEST);
        MyProgressBar.dismiss();
        if(questions == null || questions.isEmpty()){
            return;
        }
        TestData.setQuestions(new ArrayList<Question>());
        for(QuestionResponse item : questions){
            TestData.addQuestion(item);
        }
        Intent intent = new Intent(this, TestRoomActivity.class);
        intent.putExtra(AppConst.SOURCE_OBJECT_KEY, chapter);
        startActivity(intent);
    }

    private void onErrorTest(String error){
        MyProgressBar.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void storeActivity(int type){
        User user = User.getCurrentUser();
        if(user != null){
            long chapterId = ChapterDB.insert(chapter);
            long subjectId = SubjectDB.insert(chapter.getSubject());
            Activity activity = new Activity(chapter.getChapterId(), -1, type, new Date(), user.getUserId());
            boolean success = ActivityDB.update(activity);
            if(!success){
                long activityId = ActivityDB.insert(activity);
                Log.e("storeActivity", "activityId: " +activityId);
            }
            if(chapterId < 0){
                Log.e("storeActivity", "chapterId: " +chapterId);
            }
        }
    }
}
