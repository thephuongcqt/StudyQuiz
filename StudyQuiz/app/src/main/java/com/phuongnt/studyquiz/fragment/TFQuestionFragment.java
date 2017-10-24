package com.phuongnt.studyquiz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.viewmodel.Question;

public class TFQuestionFragment extends Fragment {
    private TextView tvQuestionTitle;
    private Question question;
    private TextView tvTrue;
    private TextView tvFalse;
    public TFQuestionFragment() {
        // Required empty public constructor
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
        return rootView;
    }

    private void onSelectTrue(){
        question.setSelectedAnswer(1);
        tvTrue.setBackground(getActivity().getDrawable(R.drawable.rectangle_border_selected));
        tvFalse.setBackground(getActivity().getDrawable(R.drawable.rectangle_border));
    }
    private void onSelectFalse(){
        question.setSelectedAnswer(0);
        tvTrue.setBackground(getActivity().getDrawable(R.drawable.rectangle_border));
        tvFalse.setBackground(getActivity().getDrawable(R.drawable.rectangle_border_selected));
    }

    public void setupLayout(Question question){
        this.question = question;
        if(tvQuestionTitle != null){
            tvQuestionTitle.setText(question.getValue().getTerm());
        }
    }
}
