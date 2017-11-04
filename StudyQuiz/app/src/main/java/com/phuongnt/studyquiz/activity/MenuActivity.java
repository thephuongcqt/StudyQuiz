package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.ViewPagerAdapter;
import com.phuongnt.studyquiz.fragment.ActivityFragment;
import com.phuongnt.studyquiz.fragment.SearchHistoryFragment;
import com.phuongnt.studyquiz.fragment.ProfileFragment;
import com.phuongnt.studyquiz.fragment.SubjectFragment;

public class MenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final ActivityFragment activityFragment = new ActivityFragment();
    private final SearchHistoryFragment searchHistoryFragment = new SearchHistoryFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final SubjectFragment subjectFragment = new SubjectFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityFragment.updateList();
        searchHistoryFragment.updateList();
    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(subjectFragment, "Subjects");
        adapter.addFragment(activityFragment, "Last Activity");
        adapter.addFragment(searchHistoryFragment, "Search history");
        adapter.addFragment(profileFragment, "Profile");
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

    public void onImageSearchTapped(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
