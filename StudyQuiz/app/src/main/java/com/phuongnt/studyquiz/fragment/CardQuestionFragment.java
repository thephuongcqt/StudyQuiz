package com.phuongnt.studyquiz.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.TestRoomActivity;
import com.phuongnt.studyquiz.model.viewmodel.Question;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardQuestionFragment extends Fragment {
    private TextView tvAnswers;
    private TextView tvQuestion;
    private Question question;
    private ImageView ivVolume;

    private TestRoomActivity.IFragmentLifecycleListener lifecycleListener;

    public void setLyfecycleListener(TestRoomActivity.IFragmentLifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    public CardQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card_question, container, false);
        tvAnswers = (TextView) rootView.findViewById(R.id.tv_card_answers);
        tvQuestion = (TextView) rootView.findViewById(R.id.tv_card_question);
        ivVolume = (ImageView) rootView.findViewById(R.id.iv_volume);
        lifecycleListener.onCreateViewDone();
        return rootView;
    }
    public void setupFragment(Question question){
        if(getActivity() == null || question == null || tvAnswers == null || tvQuestion == null){
            return;
        }
        this.question = question;
        if(question.getValue().getTypeId() == 0){
            setupFlashCardquestion();
        } else if(question.getValue().getTypeId() == 1){
            setupTrueFalseQuestion();
        } else{
            setupMultipleChoiceQuestion();
        }
    }
    public void setupMultipleChoiceQuestion(){
        List<String> answers = question.getAnswers();
        String answer = "";
        for(String item : answers){
            answer += item + "\n";
        }
        tvAnswers.setText(answer);
        tvQuestion.setText(question.getRealTerm());
    }
    public void setupTrueFalseQuestion(){
        tvQuestion.setText(question.getValue().getTerm());
        tvAnswers.setText("True | False");
    }
    public void setupFlashCardquestion(){
        tvQuestion.setText(question.getValue().getTerm());
        tvAnswers.setText("");
    }

    public void turnOffVolume(){
        if(getActivity() == null){
            return;
        }
        if(ivVolume != null){
            ivVolume.setImageResource(R.drawable.ic_volume_up_black);
        }
    }

    public void turnOnVolume(){
        if(getActivity() == null){
            return;
        }
        if(ivVolume != null){
            ivVolume.setImageResource(R.drawable.ic_volume_up_yellow);
        }
    }

    public String getTextToSpeech(){
        String text = tvQuestion.getText().toString();
        text += "\n" + tvAnswers.getText().toString();

        String result = "";
        Scanner scanner = new Scanner(text);
        while (scanner.hasNextLine()){
            result += scanner.nextLine() + ".";
        }
        return result.isEmpty() ? text : result;
    }
}
