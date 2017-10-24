package com.phuongnt.studyquiz.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.fragment.MCQuestionFragment;
import com.phuongnt.studyquiz.fragment.TFQuestionFragment;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.List;

public class TestRoomActivity extends AppCompatActivity {
    private static List<Question> data;
    private static int currentIndex;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private final MCQuestionFragment mcQuestionFragment = new MCQuestionFragment();
    private final TFQuestionFragment tfQuestionFragment = new TFQuestionFragment();
    private Question question = null;

    public static void setCurrentIndex(int currentIndex) {
        TestRoomActivity.currentIndex = currentIndex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);

        data = TestData.getQuestions();
        if(data == null || data.isEmpty()){
            onBackPressed();
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        question = data.get(currentIndex - 1);
        toolbarTitle.setText(currentIndex + "/" + data.size());
        changeQuestion();
    }

    public void onNextButtonSelected(View v){
        if(currentIndex < data.size()){
            currentIndex++;
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        changeQuestion();
    }

    public void onPreviousButtonSelected(View v){
        if(currentIndex > 1){
            currentIndex--;
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        changeQuestion();
    }

    public void changeQuestion(){
        if(question.getValue().getTypeId() == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.question_container, tfQuestionFragment).commit();
            tfQuestionFragment.setupLayout(question);
        } else{
            getSupportFragmentManager().beginTransaction().replace(R.id.question_container, mcQuestionFragment).commit();
        }
    }
}
