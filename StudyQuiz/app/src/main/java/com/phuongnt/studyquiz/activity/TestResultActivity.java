package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.List;

public class TestResultActivity extends AppCompatActivity {
    List<Question> questions;
    private int correctAnswer;
    private TextView tvTitle;
    private TextView tvSubtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvSubtitle = (TextView) findViewById(R.id.tv_subtitle);
        getResult();
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

        tvTitle.setText("Your score: " + (correctAnswer/questions.size() * 100) + "%" );
        tvSubtitle.setText("You are missed " + (questions.size() - correctAnswer) + " out of " + questions.size() + " questions");
    }
}
