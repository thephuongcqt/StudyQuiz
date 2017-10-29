package com.phuongnt.studyquiz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.LoginActivity;
import com.phuongnt.studyquiz.activity.MenuActivity;
import com.phuongnt.studyquiz.adapter.ActivityAdapter;
import com.phuongnt.studyquiz.model.viewmodel.Activity;

import java.util.List;

public class FirstFragment extends Fragment {
    private ListView lvActivities;
    private List<Activity> list;
    private ActivityAdapter mAdapter;
    public FirstFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        lvActivities = (ListView) rootView.findViewById(R.id.lv_activities);
        updateList();
        return rootView;
    }

    private void initFragment(){

    }

    public void updateList(){
        if(getActivity() == null){
            return;
        }
        list = Activity.getAll();
        mAdapter = new ActivityAdapter(list, getActivity());
        lvActivities.setAdapter(mAdapter);
        Log.e(FirstFragment.class + "", list.size() + "");
    }
}
