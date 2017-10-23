package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

public class DetailSubjectActivity extends AppCompatActivity {
    private static final int[] numberQuestions = {10, 15, 20, 25, 30};
    public static final String[] options = {"10", "15", "20", "25", "30"};
    public static final String KEY_SUBJECT_OBJ = "CurrentSubject";
    private SearchSubjectResponse subject = null;
    private TextView tvSubjectTitle = null;
    private Button btnStudyCard = null;
    private Button btnStartTest = null;
    private Spinner spinnerNumber = null;
    private ArrayAdapter<String> spinnerAdapter = null;
    private ArrayAdapter<String> listViewAdapter = null;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ListView lvChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            subject = (SearchSubjectResponse) bundle.get(KEY_SUBJECT_OBJ);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarTitle.setText("Detail Subject");

        lvChapters = (ListView) findViewById(R.id.lv_chapter_in_subject);
        tvSubjectTitle = (TextView) findViewById(R.id.tv_subject_title);
        btnStartTest = (Button) findViewById(R.id.btn_start_test);
        btnStudyCard = (Button) findViewById(R.id.btn_study_card);
        spinnerNumber = (Spinner) findViewById(R.id.spinner_number_question);
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, options);
        spinnerNumber.setAdapter(spinnerAdapter);
        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        lvChapters.setAdapter(listViewAdapter);

        tvSubjectTitle.setText(subject.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
