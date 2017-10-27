package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.List;

public class TestResultActivity extends AppCompatActivity {
    private Object sourceObj;

    List<Question> questions;
    private int correctAnswer;
    private TextView tvTitle;
    private TextView tvSubtitle;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        getComponent();
        initComponent();
        getResult();
    }

    private void getComponent(){
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvSubtitle = (TextView) findViewById(R.id.tv_subtitle);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            sourceObj = bundle.get(TestRoomActivity.SOURCE_OBJECT_KEY);
        }
    }

    private void initComponent(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.test_result_title);
    }

    private void getResult(){
        correctAnswer = 0;
        questions = TestData.getQuestions();
        for(Question question : questions){
            if(question.getSelectedAnswer() != null){
                if(question.getValue().getDefinition().equals(question.getSelectedAnswer() + "")){
                    correctAnswer++;
                }
            }
        }

        tvTitle.setText("Your score: " + (correctAnswer * 100)/questions.size()  + "%" );
        tvSubtitle.setText("You are missed " + (questions.size() - correctAnswer) + " out of " + questions.size() + " questions");
    }

    public void onButtonRetakeSelected(View v){
        Intent intent = new Intent(this, TestRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onButtonNewTestSelected(View v){
        Intent intent;
        if(sourceObj instanceof SearchChapterResponse){
            intent = new Intent(this, DetailChapterActivity.class);
            intent.putExtra(DetailChapterActivity.KEY_CHAPTER_OBJ, (SearchChapterResponse)sourceObj);
        } else{
            intent = new Intent(this, DetailSubjectActivity.class);
            intent.putExtra(DetailSubjectActivity.KEY_SUBJECT_OBJ, (SearchSubjectResponse)sourceObj);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
