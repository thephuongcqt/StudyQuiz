package com.phuongnt.studyquiz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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

import java.util.Collections;
import java.util.List;

public class TestRoomActivity extends AppCompatActivity {
    private static List<Question> data;
    private static int currentIndex;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private final MCQuestionFragment mcQuestionFragment = new MCQuestionFragment();
    private final TFQuestionFragment tfQuestionFragment = new TFQuestionFragment();
    private Question question = null;

    private IFragmentLyfecycleListener lyfecycleListener = new IFragmentLyfecycleListener() {
        @Override
        public void onCreateViewDone() {
            if(question.getValue().getTypeId() == 1){
                tfQuestionFragment.setupLayout(question);
            } else{
                mcQuestionFragment.setupLayout(question);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);

        data = TestData.getQuestions();
        if(data == null || data.isEmpty()){
            onBackPressed();
        }
        Collections.shuffle(data);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        currentIndex = 1;
        question = data.get(currentIndex - 1);
        toolbarTitle.setText(currentIndex + "/" + data.size());


        tfQuestionFragment.setIlyfecycleListener(lyfecycleListener);
        mcQuestionFragment.setIlyfecycleListener(lyfecycleListener);
        changeQuestion();
    }

    public void onNextButtonSelected(View v){
        if(currentIndex < data.size()){
            currentIndex++;
        } else{
            finishTest();
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

            mcQuestionFragment.setupLayout(question);
        }
    }

    private void finishTest(){
        int unselectedItems = 0;
        for(Question question : data){
            if(question.getSelectedAnswer() == null){
                unselectedItems++;
            }
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:{
                        goToTestResult();
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        String message = "Are you sure to finish this test?";
        if(unselectedItems > 0){
            message = "You are missing " + unselectedItems + " questions, Are you sure to finish?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    private void goToTestResult(){
        Intent intent = new Intent(this, TestResultActivity.class);
        startActivity(intent);
    }

    public void onQuestionSelected(View v){
        mcQuestionFragment.onQuestionSelected(v);
    }

    public interface IFragmentLyfecycleListener{
        void onCreateViewDone();
    }
}