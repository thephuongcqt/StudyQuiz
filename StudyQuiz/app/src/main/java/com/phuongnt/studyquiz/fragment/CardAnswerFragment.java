package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.TestRoomActivity;
import com.phuongnt.studyquiz.model.viewmodel.Question;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardAnswerFragment extends Fragment {
    private TextView tvAnswer;
    private Question question;
    private ImageView ivVolume;

    private TestRoomActivity.IFragmentLifecycleListener lifecycleListener;

    public void setLyfecycleListener(TestRoomActivity.IFragmentLifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    public CardAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card_answer, container, false);
        tvAnswer = (TextView)rootView.findViewById(R.id.tv_card_answer);
        ivVolume = (ImageView) rootView.findViewById(R.id.iv_volume);
        lifecycleListener.onCreateViewDone();
        return rootView;
    }

    public void setupFragment(Question question){
        if(getActivity() == null || question == null || tvAnswer == null){
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
        int index = Integer.parseInt(question.getValue().getDefinition());
        tvAnswer.setText(answers.get(index));
    }
    public void setupTrueFalseQuestion(){
        if(question.getValue().getDefinition().equals("0")){
            tvAnswer.setText("False");
        } else{
            tvAnswer.setText("True");
        }
    }
    public String getTextToSpeech(){
        String text = tvAnswer.getText().toString();
        return text;
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

    public void setupFlashCardquestion(){
        tvAnswer.setText(question.getValue().getDefinition());
    }
}
