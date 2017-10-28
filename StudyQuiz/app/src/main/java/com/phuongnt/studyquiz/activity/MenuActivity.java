package com.phuongnt.studyquiz.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.ViewPagerAdapter;
import com.phuongnt.studyquiz.fragment.FirstFragment;
import com.phuongnt.studyquiz.fragment.SecondFragment;
import com.phuongnt.studyquiz.fragment.ThirdFragment;

public class MenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private FirstFragment firstFragment;
//    private SecondFragment secondFragment;
//    private ThirdFragment thirdFragment;

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

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "Last Activity");
        adapter.addFragment(new SecondFragment(), "Search history");
        adapter.addFragment(new ThirdFragment(), "Profile");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    Fragment fragment = adapter.getItem(position);
                    if(fragment instanceof SecondFragment){
                        ((SecondFragment) fragment).updateList();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onImageSearchTapped(View v){
        Intent intent = new Intent(MenuActivity.this, SearchActivity.class);

        startActivity(intent);
    }
}
