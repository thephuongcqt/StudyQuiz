package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.fragment.CardAnswerFragment;
import com.phuongnt.studyquiz.fragment.CardQuestionFragment;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FlashCardRoomActivity extends AppCompatActivity{
    private Object sourceObj;
    private static List<Question> data;
    private static int currentIndex;
    private Question question = null;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private final CardQuestionFragment questionFragment = new CardQuestionFragment();
    private final CardAnswerFragment answerFragment = new CardAnswerFragment();
    private FrameLayout frameLayout;
    private boolean isQuestion = true;
    private TextToSpeech textToSpeech;
    private boolean isSpeaking = false;

    private TestRoomActivity.IFragmentLifecycleListener lifecycleListener = new TestRoomActivity.IFragmentLifecycleListener() {
        @Override
        public void onCreateViewDone() {
            if(isQuestion){
                questionFragment.setupFragment(question);
            } else{
                answerFragment.setupFragment(question);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_room);

        initTTS();
        getComponent();
        initComponent();
    }

    private void initTTS(){
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.UK);

                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            Log.e("onStart", "done");
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            Log.e("onDone", "done");
                            stopSpeaking();
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.e("onError", "done");
                            stopSpeaking();
                        }
                    });
                }
            }
        });
    }

    private void getComponent(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        frameLayout = (FrameLayout) findViewById(R.id.card_container);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            sourceObj = bundle.get(AppConst.SOURCE_OBJECT_KEY);
        }
    }

    private void initComponent(){

        data = TestData.getQuestions();
        if(data == null || data.isEmpty()){
            onBackPressed();
        }
        Collections.shuffle(data);
//        TestData.resetAnswer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        currentIndex = 1;
        question = data.get(currentIndex - 1);
        toolbarTitle.setText(currentIndex + "/" + data.size());

        answerFragment.setLyfecycleListener(lifecycleListener);
        questionFragment.setLyfecycleListener(lifecycleListener);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.card_container, questionFragment)
                .commit();

        questionFragment.setupFragment(question);
    }

    public void onPreviousButtonSelected(View v){
        stopSpeaking();
        isQuestion = true;
        if(currentIndex > 1){
            currentIndex--;
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.card_container, questionFragment)
                .commit();
        questionFragment.setupFragment(question);
    }

    public void onNextButtonSelected(View v){
        stopSpeaking();
        isQuestion = true;
        if(currentIndex < data.size()){
            currentIndex++;
        } else{
            goToFlashCardDone();
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.card_container, questionFragment)
                .commit();
        questionFragment.setupFragment(question);
    }

    private void goToFlashCardDone(){
        Intent intent = new Intent(this, FlashCardDoneActivity.class);
        if(sourceObj instanceof SearchSubjectResponse){
            intent.putExtra(AppConst.SOURCE_OBJECT_KEY, (SearchSubjectResponse)sourceObj);
        } else if(sourceObj instanceof SearchChapterResponse){
            intent.putExtra(AppConst.SOURCE_OBJECT_KEY, (SearchChapterResponse)sourceObj);
        }
        startActivity(intent);
    }

    public void onFragmentAnswerSelected(View v){
        isQuestion = true;
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.card_container, questionFragment)
                .commit();
        questionFragment.setupFragment(question);
        stopSpeaking();
    }

    public void onFragentQuestionSelected(View v){
        isQuestion = false;
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)
                .replace(R.id.card_container, answerFragment)
                .commit();
        answerFragment.setupFragment(question);
        stopSpeaking();
    }

    public void onBackPressed() {
        super.onBackPressed();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void onVolumeQuestionSelected(View v){
        if(isSpeaking){
            isSpeaking = false;
            textToSpeech.stop();
        } else{
            isSpeaking = true;
            String text = questionFragment.getTextToSpeech();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    public void onVolumeAnswerSelected(View v){
        if(isSpeaking){
            isSpeaking = false;
            textToSpeech.stop();
        } else{
            isSpeaking = true;
            String text = answerFragment.getTextToSpeech();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void stopSpeaking(){
        isSpeaking = false;
        textToSpeech.stop();
    }

    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

//    @Override
//    public void onInit(int status) {
//        if(status == TextToSpeech.SUCCESS) {
//            textToSpeech.setLanguage(Locale.UK);
//            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
//                @Override
//                public void onStart(String utteranceId) {
//                    // Speaking started.
//                    Log.e("onstart", "done");
//                }
//
//                @Override
//                public void onDone(String utteranceId) {
//                    // Speaking stopped.
//                    Log.e("onDone", "done");
//                    stopSpeaking();
//                }
//
//                @Override
//                public void onError(String utteranceId) {
//                    // There was an error.
//                }
//
//            } );
//        }
//    }
}
