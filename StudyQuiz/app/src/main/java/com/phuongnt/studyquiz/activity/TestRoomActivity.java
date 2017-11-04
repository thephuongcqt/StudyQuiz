package com.phuongnt.studyquiz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.fragment.MCQuestionFragment;
import com.phuongnt.studyquiz.fragment.TFQuestionFragment;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.TestData;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TestRoomActivity extends AppCompatActivity {

    private Object sourceObj;

    private static List<Question> data;
    private static int currentIndex;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private final MCQuestionFragment mcQuestionFragment = new MCQuestionFragment();
    private final TFQuestionFragment tfQuestionFragment = new TFQuestionFragment();
    private Question question = null;

    private TextToSpeech textToSpeech;
    private boolean isSpeaking = false;

    private IFragmentLifecycleListener lifecycleListener = new IFragmentLifecycleListener() {
        @Override
        public void onCreateViewDone() {
            if(question.getValue().getTypeId() == 1){
                tfQuestionFragment.setupLayout(question);
            } else{
                mcQuestionFragment.setupLayout(question);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);

        initTTS();
        getComponent();
        initComponent();
        changeQuestion();
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
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            stopSpeaking();
                        }

                        @Override
                        public void onError(String utteranceId) {
//                            stopSpeaking();
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
        TestData.resetAnswer();

        tfQuestionFragment.setIlyfecycleListener(lifecycleListener);
        mcQuestionFragment.setIlyfecycleListener(lifecycleListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        currentIndex = 1;
        question = data.get(currentIndex - 1);
        toolbarTitle.setText(currentIndex + "/" + data.size());
    }

    public void onNextButtonSelected(View v){
        stopSpeaking();
        if(currentIndex < data.size()){
            currentIndex++;
        } else{
            finishTest();
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        changeQuestion();
    }

    public void onPreviousButtonSelected(View v){
        stopSpeaking();
        if(currentIndex > 1){
            currentIndex--;
        }
        toolbarTitle.setText(currentIndex + "/" + data.size());
        question = data.get(currentIndex - 1);
        changeQuestion();
    }

    public void changeQuestion(){
        if(question.getValue().getTypeId() == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.question_container, tfQuestionFragment).commit();
            tfQuestionFragment.setupLayout(question);
        } else{
            getSupportFragmentManager().beginTransaction().replace(R.id.question_container, mcQuestionFragment).commit();

            mcQuestionFragment.setupLayout(question);
        }
    }

    private void finishTest(){
        int unselectedItems = 0;
        for(Question question : data){
            if(question.getSelectedAnswer() == null){
                unselectedItems++;
            }
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:{
                        goToTestResult();
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        String message = "Are you sure to finish this test?";
        if(unselectedItems > 0){
            message = "You are missing " + unselectedItems + " questions, Are you sure to finish?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    private void goToTestResult(){
        Intent intent = new Intent(this, TestResultActivity.class);
        if(sourceObj instanceof SearchSubjectResponse){
            intent.putExtra(AppConst.SOURCE_OBJECT_KEY, (SearchSubjectResponse)sourceObj);
        } else if(sourceObj instanceof SearchChapterResponse){
            intent.putExtra(AppConst.SOURCE_OBJECT_KEY, (SearchChapterResponse)sourceObj);
        }
        startActivity(intent);
    }

    public void onQuestionSelected(View v){
        mcQuestionFragment.onQuestionSelected(v);
    }

    public interface IFragmentLifecycleListener{
        void onCreateViewDone();
    }

    public void onBackPressed() {
        super.onBackPressed();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void onMCQuestionVolumeClick(View v){
        if(isSpeaking){
            stopSpeaking();
        } else {
            isSpeaking = true;
            mcQuestionFragment.turnOnVolume();
            String text = mcQuestionFragment.getTextToSpeech();

            Bundle params = new Bundle();
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"UniqueID");
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, params, "UniqueID");
        }
    }

    public void onTFQuestionVolumeClick(View v){
        if(isSpeaking){
            stopSpeaking();
        } else{
            isSpeaking = true;
            tfQuestionFragment.turnOnVolume();
            String text = tfQuestionFragment.getTextToSpeech();

            Bundle params = new Bundle();
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"UniqueID");
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, params, "UniqueID");
        }
    }

    private void stopSpeaking(){
        isSpeaking = false;
        try{
            if(textToSpeech.isSpeaking()){
                textToSpeech.stop();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mcQuestionFragment.turnOffVolume();
                    tfQuestionFragment.turnOffVolume();
                }
            });
        } catch(Exception e){
            Log.e("stop", e.getMessage());
        }
    }

    public void onPause(){
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
