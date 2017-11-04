package com.phuongnt.studyquiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.LoginActivity;
import com.phuongnt.studyquiz.database.UserDB;
import com.phuongnt.studyquiz.model.viewmodel.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Button mbuttonLogout;
    private User currentUser;
    private TextView tvUsername;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPassword;


    public ProfileFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        getComponent(rootView);
        initComponent();
        return rootView;
    }

    private void getComponent(View root){
        mbuttonLogout = (Button) root.findViewById(R.id.btn_Logout);
        tvUsername = (TextView) root.findViewById(R.id.tv_username);
        tvName = (TextView) root.findViewById(R.id.tv_name);
        tvEmail = (TextView) root.findViewById(R.id.tv_email);
        tvPassword = (TextView) root.findViewById(R.id.tv_password);
        mbuttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLogoutSelected();
            }
        });
    }

    private void initComponent(){
        currentUser = User.getCurrentUser();
        if(currentUser == null){
            onButtonLogoutSelected();
        }
        tvUsername.setText(currentUser.getUsername());
        tvName.setText(currentUser.getName());
        tvEmail.setText(currentUser.getEmail());
        tvPassword.setText("************");
    }

    private void onButtonLogoutSelected(){
        new UserDB().truncate();
        User.deleteCurrentUser();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
