package com.phuongnt.studyquiz.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.adapter.ViewPagerAdapter;
import com.phuongnt.studyquiz.fragment.SearchChapterFragment;
import com.phuongnt.studyquiz.fragment.SearchSubjectFragment;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.service.MyProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<SearchChapterResponse> chapters = null;
    private List<SearchSubjectResponse> subjects = null;
    private SearchChapterFragment chapterFragment = null;
    private SearchSubjectFragment subjectFragment = null;
    private String searchValue = null;
    private int progressCount;
    private int chapterOffset = 0;
    private int subjectOffset = 0;

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
        chapterFragment.setButtonClickListener(buttonChapterListener);
        subjectFragment.setButtonClickListener(buttonSubjectListener);
        adapter.addFragment(subjectFragment, "Subject");
        adapter.addFragment(chapterFragment, "Chapter");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void imageSearchSelected(View v){
        dismissKeyboard();
        searchValue = ((EditText) findViewById(R.id.edt_search)).getText().toString().trim();
        if(searchValue.isEmpty()){
            return;
        }
        MyProgressBar.show(this);
        progressCount = 2;
        searchChapter();
        searchSubject();
    }

    private void searchChapter(){
        try{
            Map<String, String> request = new HashMap<>();
            request.put(RequestParam.SEARCH_SEARCH_VALUE, searchValue);
            request.put(RequestParam.SEARCH_OFFSET, chapterOffset + "");
            request.put(RequestParam.SEARCH_NUMBER, AppConst.SEARCH_ITEMS_NUMBER + "");
            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchChapterResponse>>> call = iapiHelper.searchChapter(request);
            call.enqueue(new Callback<CommonResponse<List<SearchChapterResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchChapterResponse>>> call, Response<CommonResponse<List<SearchChapterResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchChapterResponse>> commonResponse= response.body();
                        if(commonResponse.isSuccess()){
                            onChapterSuccess(commonResponse.getValue());
                        } else {
                            onChapterError(commonResponse.getError());
                        }
                    } else{
                        onChapterError("Connection error");
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse<List<SearchChapterResponse>>> call, Throwable t) {
                    onChapterError(t.getMessage());
                }
            });
        } catch(Exception e){
            onChapterError(e.getMessage());
        }
    }

    private void searchSubject(){
        try{
            Map<String, String> request = new HashMap<>();
            request.put(RequestParam.SEARCH_SEARCH_VALUE, searchValue);
            request.put(RequestParam.SEARCH_OFFSET, subjectOffset + "");
            request.put(RequestParam.SEARCH_NUMBER, AppConst.SEARCH_ITEMS_NUMBER + "");

            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchSubjectResponse>>> call = iapiHelper.searchSubject(request);
            call.enqueue(new Callback<CommonResponse<List<SearchSubjectResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchSubjectResponse>>> call, Response<CommonResponse<List<SearchSubjectResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchSubjectResponse>> commonResponse= response.body();
                        if(commonResponse.isSuccess()){
                            onSubjectSuccess(subjects = commonResponse.getValue());
                        } else {
                            onSubjectError(commonResponse.getError());
                        }
                    } else{
                        onSubjectError("Connection error");
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse<List<SearchSubjectResponse>>> call, Throwable t) {
                    onSubjectError(t.getMessage());
                }
            });
        } catch(Exception e){
            onSubjectError(e.getMessage());
        }
    }

    private void onSubjectSuccess(List<SearchSubjectResponse> subjectsResponse){
        if(subjects == null){
            subjects = new ArrayList<>();
        }
        subjects.addAll(subjectsResponse);
        subjectFragment.setupListView(subjects);
        whenProgressDone();
    }

    private void onSubjectError(String error){
        whenProgressDone();
        Toast.makeText(this, error, Toast.LENGTH_LONG);
    }

    private void onChapterSuccess(List<SearchChapterResponse> chaptersResponse){
        if(chapters == null){
            chapters = new ArrayList<>();
        }
        chapters.addAll(chaptersResponse);
        chapterFragment.setupListView(chapters);
        whenProgressDone();
    }

    private void onChapterError(String error){
        whenProgressDone();
        Toast.makeText(this, error, Toast.LENGTH_LONG);
    }

    private void whenProgressDone(){
        progressCount--;
        if(progressCount <= 0){
            MyProgressBar.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private SearchAdapter.IButtonClickListener buttonChapterListener = new SearchAdapter.IButtonClickListener() {
        @Override
        public void buttonClickAt(int position) {
            SearchChapterResponse item = chapters.get(position);
            if(item.getSubject() != null){

            }
        }
    };

    private SearchAdapter.IButtonClickListener buttonSubjectListener = new SearchAdapter.IButtonClickListener() {
        @Override
        public void buttonClickAt(int position) {
            SearchSubjectResponse item = subjects.get(position);
            chapters = item.getChapters();
            for(SearchChapterResponse i : chapters){
                i.setSubject(item);
            }
            chapterFragment.setupListView(chapters);
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();
        }
    };
}
