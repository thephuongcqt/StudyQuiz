package com.phuongnt.studyquiz.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.LoginActivity;
import com.phuongnt.studyquiz.database.UserDB;
import com.phuongnt.studyquiz.model.viewmodel.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private Button mbuttonLogout;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        mbuttonLogout = (Button) rootView.findViewById(R.id.btn_Logout);
        mbuttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLogoutSelected();
            }
        });
        return rootView;
    }

    private void onButtonLogoutSelected(){
        new UserDB().truncate();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    public void btnLogOutSelected(View v){

    }
}
