package com.phuongnt.studyquiz.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.transition.Fade;
import android.support.transition.Transition;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.ViewPagerAdapter;
import com.phuongnt.studyquiz.fragment.SearchChapterFragment;
import com.phuongnt.studyquiz.fragment.SearchSubjectFragment;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.service.MyProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<SearchChapterResponse> chapters = null;
    private List<SearchSubjectResponse> subjects = null;
    private List<String> lvChapters = null;
    private List<String> lvSubjects = null;
    private SearchChapterFragment chapterFragment = null;
    private SearchSubjectFragment subjectFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        setupWindowAnimations();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupWindowAnimations() {
//        Fade fade = new Fade();
//        fade.setDuration(1000);
//        getWindow().setEnterTransition();
//        Slide slide = new Slide();
//        slide.setDuration(3000);
//        getWindow().setEnterTransition(slide);
    }

    private void dismissKeyboard(){
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        chapterFragment = new SearchChapterFragment();
        subjectFragment = new SearchSubjectFragment();
        adapter.addFragment(chapterFragment, "Chapter");
        adapter.addFragment(subjectFragment, "Subject");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 1){
                    searchSubject();
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    searchSubject();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void imageSearchSelected(View v){
        dismissKeyboard();
        lvChapters = null;
        lvSubjects = null;
        String searchValue = ((EditText) findViewById(R.id.edt_search)).getText().toString();
        MyProgressBar.show(this);
        try{
            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchChapterResponse>>> call = iapiHelper.searchChapter(searchValue);
            call.enqueue(new Callback<CommonResponse<List<SearchChapterResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchChapterResponse>>> call, Response<CommonResponse<List<SearchChapterResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchChapterResponse>> commonResponse= response.body();
                        if(commonResponse.isSuccess()){
                            chapters = commonResponse.getValue();
                            onSuccess();
                        } else {
                            onError(commonResponse.getError());
                        }
                    } else{
                        onError("Connection error");
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse<List<SearchChapterResponse>>> call, Throwable t) {
                    onError(t.getMessage());
                }
            });
        } catch(Exception e){
            onError(e.getMessage());
        }
    }

    private void searchSubject(){
        String searchValue = ((EditText) findViewById(R.id.edt_search)).getText().toString();
        MyProgressBar.show(this);
        try{
            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchSubjectResponse>>> call = iapiHelper.searchSubject(searchValue);
            call.enqueue(new Callback<CommonResponse<List<SearchSubjectResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchSubjectResponse>>> call, Response<CommonResponse<List<SearchSubjectResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchSubjectResponse>> commonResponse= response.body();
                        if(commonResponse.isSuccess()){
                            subjects = commonResponse.getValue();
                            onSuccess();
                        } else {
                            onError(commonResponse.getError());
                        }
                    } else{
                        onError("Connection error");
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse<List<SearchSubjectResponse>>> call, Throwable t) {
                    onError(t.getMessage());
                }
            });
        } catch(Exception e){
            onError(e.getMessage());
        }
    }

    private void onSuccess(){
        if(chapters != null && lvChapters == null){
            lvChapters = new ArrayList<>();
            for (SearchChapterResponse item : chapters) {
                lvChapters.add(item.getName());
            }
            chapterFragment.setupListView(lvChapters);
        }
        if(subjects != null && lvSubjects == null){
            lvSubjects = new ArrayList<>();
            for(SearchSubjectResponse item : subjects){
                lvSubjects.add(item.getName());
            }
            subjectFragment.setupListView(lvSubjects);
        }
        MyProgressBar.dismiss();
    }

    private void onError(String error){
        MyProgressBar.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        im.showSoftInput(null, 0);
    }
}
