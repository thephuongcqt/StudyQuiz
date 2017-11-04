package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.AnswerAdapter;
import com.phuongnt.studyquiz.utils.SingleScrollListView;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.List;

public class ReviewAnswersActivity extends AppCompatActivity {
    private SingleScrollListView lvAnswers;
    private List<Question> data;
    private AnswerAdapter adapter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private IClickListener ivClickListener = new IClickListener() {
        @Override
        public void onImageFeedbackSelected(Question question) {
            Intent intent = new Intent(ReviewAnswersActivity.this, FeedbackActivity.class);
            intent.putExtra(AppConst.SOURCE_OBJECT_KEY, question);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_answers);

        getComponent();
        initComponent();
    }
    private void getComponent(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        lvAnswers = (SingleScrollListView) findViewById(R.id.lv_answers);
    }
    private void initComponent(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.review_answer_toolbar_title);

        TestData.checkAnswer();
        data = TestData.getQuestions();
        adapter = new AnswerAdapter(data, this, ivClickListener);
        lvAnswers.setAdapter(adapter);
        lvAnswers.setSingleScroll(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public interface IClickListener{
        void onImageFeedbackSelected(Question question);
    }
}

