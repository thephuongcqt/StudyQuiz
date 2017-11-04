package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.TestRoomActivity;
import com.phuongnt.studyquiz.model.viewmodel.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MCQuestionFragment extends Fragment {
    private Question question;
    private LinearLayout answerContainer;
    private List<TextView> tvAnswers;
    private TextView tvQuestionTitle;
    private ImageView ivVolume;

    private TestRoomActivity.IFragmentLifecycleListener lifecycleListener;

    public void setIlyfecycleListener(TestRoomActivity.IFragmentLifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    public MCQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        ivVolume = (ImageView) rootView.findViewById(R.id.iv_volume);
        tvQuestionTitle = (TextView) rootView.findViewById(R.id.tv_question_title);
        answerContainer = (LinearLayout) rootView.findViewById(R.id.ll_container);
        TextView textView0 = (TextView) rootView.findViewById(R.id.answer_item0);
        TextView textView1 = (TextView) rootView.findViewById(R.id.answer_item1);
        TextView textView2 = (TextView) rootView.findViewById(R.id.answer_item2);
        TextView textView3 = (TextView) rootView.findViewById(R.id.answer_item3);
        TextView textView4 = (TextView) rootView.findViewById(R.id.answer_item4);
        TextView textView5 = (TextView) rootView.findViewById(R.id.answer_item5);

        tvAnswers = new ArrayList<>();
        tvAnswers.add(textView0);
        tvAnswers.add(textView1);
        tvAnswers.add(textView2);
        tvAnswers.add(textView3);
        tvAnswers.add(textView4);
        tvAnswers.add(textView5);

        if(lifecycleListener != null){
            lifecycleListener.onCreateViewDone();
        }
        return rootView;
    }

    private void selectItem(int index){
        question.setSelectedAnswer(index);
        TextView item = tvAnswers.get(index);
        if(item != null){
            item.setBackground(getActivity().getDrawable(R.drawable.border_yellow));
        }
    }

    private void deselectItem(TextView item){
        if(item != null){
            item.setBackground(getActivity().getDrawable(R.drawable.border_gray));
        }
    }

    private void resetAnswers(){
        for (TextView item: tvAnswers) {
            deselectItem(item);
        }
    }

    private void hideAnswer(TextView item){
        if(item != null){
            item.setVisibility(View.GONE);
        }
    }

    private void initAnswers(){
        List<String> answers = question.getAnswers();
        for(int i = 0; i < answers.size(); i++){
            tvAnswers.get(i).setText(answers.get(i));
        }
        for(int i = answers.size(); i < tvAnswers.size(); i++){
            hideAnswer(tvAnswers.get(i));
        }
    }

    public void setupLayout(Question question){
        if(getActivity() == null || question == null){
            return;
        }
        this.question = question;
        resetAnswers();
        if(question.getSelectedAnswer() != null){
            selectItem(question.getSelectedAnswer());
        }
        tvQuestionTitle.setText(question.getRealTerm());
        initAnswers();
    }

    public void onQuestionSelected(View v){
        resetAnswers();
        switch (v.getId()){
            case R.id.answer_item0:{
                selectItem(0);
                break;
            }
            case R.id.answer_item1:{
                selectItem(1);
                break;
            }
            case R.id.answer_item2:{
                selectItem(2);
                break;
            }
            case R.id.answer_item3:{
                selectItem(3);
                break;
            }
            case R.id.answer_item4:{
                selectItem(4);
                break;
            }
            case R.id.answer_item5:{
                selectItem(5);
                break;
            }
        }
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
        return question.getValue().getTerm();
    }
}
