package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
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

public class DetailSubjectActivity extends AppCompatActivity {
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
            subject = (SearchSubjectResponse) bundle.get(AppConst.KEY_SUBJECT_OBJ);
            if(subject == null){
                onBackPressed();
                return;
            }
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
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, AppConst.OPTIONS);
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
                intent.putExtra(AppConst.KEY_CHAPTER_OBJ, chapter);
                startActivity(intent);
            }
        });
    }

    public void onButtonStudyCardSelected(View v){
        int index = spinnerNumber.getSelectedItemPosition();
        int number = AppConst.NUMBER_QUESTIONS[index];
        User user = User.getCurrentUser();
        if(user == null){
            return;
        }
//        Intent intent = new Intent(this, FlashCardRoomActivity.class);
//        startActivity(intent);
        MyProgressBar.show(this);

        Map<String, String> params = new HashMap<>();
        params.put(RequestParam.SUBJECT_SUBJECTID, subject.getSubjectId() + "");
        params.put(RequestParam.SUBJECT_NUMBER, number + "");
        params.put(RequestParam.SUBJECT_USERID, user.getUserId() + "");

        IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
        Call<CommonResponse<List<QuestionResponse>>> call = iapiHelper.getSubjectCards(params);
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
        intent.putExtra(AppConst.SOURCE_OBJECT_KEY, subject);
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
            Toast.makeText(this, "No question to test", Toast.LENGTH_SHORT).show();
            return;
        }
        TestData.setQuestions(new ArrayList<Question>());
        for(QuestionResponse item : questions){
            TestData.addQuestion(item);
        }
        Intent intent = new Intent(this, TestRoomActivity.class);
        intent.putExtra(AppConst.SOURCE_OBJECT_KEY, subject);
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
            long subjectId = SubjectDB.insert(subject);
            for(SearchChapterResponse item : subject.getChapters()){
                ChapterDB.insert(item);
            }
            Activity activity = new Activity(-1, subject.getSubjectId(), type, new Date(), user.getUserId());
            boolean success = ActivityDB.update(activity);
            if(!success){
                long activityId = ActivityDB.insert(activity);
                Log.e("storeActivity", "activityId: " +activityId);
            }
        }
    }
}
