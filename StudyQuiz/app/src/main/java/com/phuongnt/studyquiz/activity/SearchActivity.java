package com.phuongnt.studyquiz.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.ViewPagerAdapter;
import com.phuongnt.studyquiz.database.SearchHistoryDB;
import com.phuongnt.studyquiz.database.UserDB;
import com.phuongnt.studyquiz.fragment.SearchChapterFragment;
import com.phuongnt.studyquiz.fragment.SearchSubjectFragment;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.SearchHistory;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import java.util.ArrayList;
import java.util.Date;
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
    private EditText edtSearch;
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

        getComponent();
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(v != null){
                        dismissKeyboard();
                    }
                    searchValue = (edtSearch).getText().toString().trim();
                    if(searchValue.isEmpty()){
                        return false;
                    }
                    MyProgressBar.show(SearchActivity.this);
                    progressCount = 2;
                    searchChapter();
                    searchSubject();
                }
                return false;

            }
        });
        initComponent();
    }

    private void getComponent(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        edtSearch = (EditText) findViewById(R.id.edt_search);
    }

    private void initComponent(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            searchValue = bundle.getString(AppConst.SEARCH_VALUE);
            if(searchValue != null){
                edtSearch.setText(searchValue);
                imageSearchSelected(null);
            }
        }
    }

    private void dismissKeyboard(){
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager.isAcceptingText()){
            inputManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else{

        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        chapterFragment = new SearchChapterFragment();
        subjectFragment = new SearchSubjectFragment();
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
        if(v != null){
            dismissKeyboard();
        }
        searchValue = (edtSearch).getText().toString().trim();
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
            Map<String, String> params = new HashMap<>();
            params.put(RequestParam.SEARCH_SEARCH_VALUE, searchValue);
            params.put(RequestParam.SEARCH_OFFSET, chapterOffset + "");
            params.put(RequestParam.SEARCH_NUMBER, AppConst.SEARCH_ITEMS_NUMBER + "");

            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchChapterResponse>>> call = iapiHelper.searchChapter(params);
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
                        onChapterError(AppConst.ERROR_CONNECTION);
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
            Map<String, String> params = new HashMap<>();
            params.put(RequestParam.SEARCH_SEARCH_VALUE, searchValue);
            params.put(RequestParam.SEARCH_OFFSET, subjectOffset + "");
            params.put(RequestParam.SEARCH_NUMBER, AppConst.SEARCH_ITEMS_NUMBER + "");

            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchSubjectResponse>>> call = iapiHelper.searchSubject(params);
            call.enqueue(new Callback<CommonResponse<List<SearchSubjectResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchSubjectResponse>>> call, Response<CommonResponse<List<SearchSubjectResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchSubjectResponse>> commonResponse= response.body();
                        if(commonResponse.isSuccess()){
                            onSubjectSuccess(commonResponse.getValue());
                        } else {
                            onSubjectError(commonResponse.getError());
                        }
                    } else{
                        onSubjectError(AppConst.ERROR_CONNECTION);
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
            dismissKeyboard();
            if(chapters != null && subjects != null && (!chapters.isEmpty() || !subjects.isEmpty())){
                storeToSearchHistory();
            }
        }
    }

    private void storeToSearchHistory(){
        User user = User.getCurrentUser();
        SearchHistory item = new SearchHistory(searchValue, user.getUserId(), new Date());
        boolean success = SearchHistoryDB.update(item);
        if(!success){
            long searchId = SearchHistoryDB.insert(item);
            if(searchId < 0){
                Log.e("storeToSearchHistory", item.toString());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
