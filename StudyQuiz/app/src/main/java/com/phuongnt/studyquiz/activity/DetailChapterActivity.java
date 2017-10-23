package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;

import org.w3c.dom.Text;

public class DetailChapterActivity extends AppCompatActivity {
    private static final int[] numberQuestions = {10, 15, 20, 25, 30};
    public static final String[] options = {"10", "15", "20", "25", "30"};
    public static final String KEY_CHAPTER_OBJ = "currentChapter";
    private SearchChapterResponse chapter = null;
    private TextView tvSubjectTitle = null;
    private TextView tvChapterTitle = null;
    private Button btnStudyCard = null;
    private Button btnStartTest = null;
    private Spinner spinnerNumber = null;
    private ArrayAdapter<String> adapter = null;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            chapter = (SearchChapterResponse) bundle.get(KEY_CHAPTER_OBJ);
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Detail Chapter");

        tvSubjectTitle = (TextView) findViewById(R.id.tv_subject_title);
        tvChapterTitle = (TextView) findViewById(R.id.tv_chapter_title);
        btnStudyCard = (Button) findViewById(R.id.btn_study_card);
        btnStartTest = (Button) findViewById(R.id.btn_start_test);
        spinnerNumber = (Spinner) findViewById(R.id.spinner_number_question);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options);
        spinnerNumber.setAdapter(adapter);
        tvSubjectTitle.setText(chapter.getSubject() == null ? "" : chapter.getSubject().getName());
        tvChapterTitle.setText(chapter.getName());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
