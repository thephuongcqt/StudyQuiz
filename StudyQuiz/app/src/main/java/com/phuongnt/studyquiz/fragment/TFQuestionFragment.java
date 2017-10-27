package com.phuongnt.studyquiz.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.TestRoomActivity;
import com.phuongnt.studyquiz.model.viewmodel.Question;

public class TFQuestionFragment extends Fragment {
    private TextView tvQuestionTitle;
    private Question question;
    private TextView tvTrue;
    private TextView tvFalse;
    public TFQuestionFragment() {
        // Required empty public constructor
    }
    private TestRoomActivity.IFragmentLyfecycleListener ilyfecycleListener;

    public void setIlyfecycleListener(TestRoomActivity.IFragmentLyfecycleListener ilyfecycleListener) {
        this.ilyfecycleListener = ilyfecycleListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_tfquestion, container, false);
        tvQuestionTitle = (TextView)rootView.findViewById(R.id.tv_question_title);
        tvTrue = (TextView) rootView.findViewById(R.id.tv_True);
        tvFalse = (TextView) rootView.findViewById(R.id.tv_False);
        tvTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                onSelectTrue();
            }
        });

        tvFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectFalse();
            }
        });

        ilyfecycleListener.onCreateViewDone();
        return rootView;
    }

    private void resetSelected(){
        if(tvTrue != null){
            tvTrue.setBackground(getActivity().getDrawable(R.drawable.border_gray));
        }
        if(tvFalse != null){
            tvFalse.setBackground(getActivity().getDrawable(R.drawable.border_gray));
        }
    }
    private void selectAnswer(int index){
        question.setSelectedAnswer(index);
        if(index == 0){
            if(tvFalse != null){
                tvFalse.setBackground(getActivity().getDrawable(R.drawable.border_yellow));
            }
        } else{
            if(tvTrue != null){
                tvTrue.setBackground(getActivity().getDrawable(R.drawable.border_yellow));
            }
        }
    }

    private void onSelectTrue(){
        resetSelected();
        selectAnswer(1);

    }
    private void onSelectFalse(){
        resetSelected();
        selectAnswer(0);
    }

    public void setupLayout(Question question){
        if(getActivity() == null || question == null){
            return;
        }
        resetSelected();
        this.question = question;
        if(tvQuestionTitle != null){
            tvQuestionTitle.setText(question.getValue().getTerm());
        }
        if(question.getSelectedAnswer() != null){
            selectAnswer(question.getSelectedAnswer());
        }
    }
}
