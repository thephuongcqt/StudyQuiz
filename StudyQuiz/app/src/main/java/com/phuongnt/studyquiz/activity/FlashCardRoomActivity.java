package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.fragment.CardAnswerFragment;
import com.phuongnt.studyquiz.fragment.CardQuestionFragment;

public class FlashCardRoomActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private final CardQuestionFragment questionFragment = new CardQuestionFragment();
    private final CardAnswerFragment answerFragment = new CardAnswerFragment();
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_room);

        getComponent();
        initComponent();
    }
    private void getComponent(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        frameLayout = (FrameLayout) findViewById(R.id.card_container);
    }

    private void initComponent(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText("Flash card");

        getFragmentManager()
                .beginTransaction()
                .add(R.id.card_container, questionFragment)
                .commit();
    }

    public void onPreviousButtonSelected(View v){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.card_container, questionFragment)
                .commit();
    }

    public void onNextButtonSelected(View v){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.card_container, questionFragment)
                .commit();
    }

    public void onFragmentAnswerSelected(View v){
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.card_container, questionFragment)
                .commit();
    }

    public void onFragentQuestionSelected(View v){
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)
                .replace(R.id.card_container, answerFragment)
                .commit();
    }
}
