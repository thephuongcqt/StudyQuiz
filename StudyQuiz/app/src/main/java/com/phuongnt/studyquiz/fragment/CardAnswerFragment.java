package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnt.studyquiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardAnswerFragment extends Fragment {


    public CardAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_answer, container, false);
    }

}
