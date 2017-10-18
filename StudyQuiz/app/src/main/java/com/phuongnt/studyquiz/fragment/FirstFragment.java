package com.phuongnt.studyquiz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.LoginActivity;
import com.phuongnt.studyquiz.activity.MenuActivity;

public class FirstFragment extends Fragment {

    public FirstFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}
