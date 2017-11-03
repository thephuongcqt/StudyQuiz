package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

public class FlashCardDoneActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Object sourceObj;
    private TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_done);
//        saveStudiedQuestion();
        getComponent();
        initComponent();
    }

    private void saveStudiedQuestion() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TestData.saveStudiedQuestions();
                } catch(Exception e){
                    Log.e("TestResult_Save", e.getMessage());
                }
            }
        });
        thread.start();
    }

    private void getComponent(){
        tvTitle = (TextView) findViewById(R.id.tv_done_title);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            sourceObj = bundle.get(AppConst.SOURCE_OBJECT_KEY);
        }
    }

    private void initComponent(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        toolbarTitle.setText(R.string.test_result_title);

        int count = TestData.getQuestions().size();
        tvTitle.setText("You just studies " + count + " terms");
    }

    public void onButtonStudyAgainSelected(View v){
        Intent intent = new Intent(this, FlashCardRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void onButtonStudyNewSelected(View v){
        Intent intent;
        if(sourceObj instanceof SearchChapterResponse){
            intent = new Intent(this, DetailChapterActivity.class);
            intent.putExtra(AppConst.KEY_CHAPTER_OBJ, (SearchChapterResponse)sourceObj);
        } else{
            intent = new Intent(this, DetailSubjectActivity.class);
            intent.putExtra(AppConst.KEY_SUBJECT_OBJ, (SearchSubjectResponse)sourceObj);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onButtonStudyNewSelected(null);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
