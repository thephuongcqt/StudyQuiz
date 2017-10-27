package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.AnswerAdapter;
import com.phuongnt.studyquiz.adapter.SingleScrollListView;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.List;

public class ReviewAnswersActivity extends AppCompatActivity {
    private SingleScrollListView lvAnswers;
    private List<Question> data;
    private AnswerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_answers);

        getComponent();
        initComponent();
    }
    private void getComponent(){
        lvAnswers = (SingleScrollListView) findViewById(R.id.lv_answers);

    }
    private void initComponent(){
        TestData.checkAnswer();
        data = TestData.getQuestions();
        adapter = new AnswerAdapter(data, this);
        lvAnswers.setAdapter(adapter);
        lvAnswers.setSingleScroll(true);
    }
}

