package com.phuongnt.studyquiz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.DetailChapterActivity;
import com.phuongnt.studyquiz.activity.DetailSubjectActivity;
import com.phuongnt.studyquiz.adapter.ActivityAdapter;
import com.phuongnt.studyquiz.model.viewmodel.Activity;

import java.util.List;

public class ActivityFragment extends Fragment {
    private ListView lvActivities;
    private List<Activity> list;
    private ActivityAdapter mAdapter;
    public ActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);
        lvActivities = (ListView) rootView.findViewById(R.id.lv_activities);
        lvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity item = list.get(position);
                Intent intent;
                if(item.getChapterId() >= 0){
                    intent = new Intent(getActivity(), DetailChapterActivity.class);
                    intent.putExtra(AppConst.KEY_CHAPTER_OBJ, item.getChapter());
                } else{
                    intent = new Intent(getActivity(), DetailSubjectActivity.class);
                    intent.putExtra(AppConst.KEY_SUBJECT_OBJ, item.getSubject());
                }
                startActivity(intent);
            }
        });
        updateList();
        if(mAdapter != null){
            lvActivities.setAdapter(mAdapter);
        }
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
        Log.e(ActivityFragment.class + "", list.size() + "");
    }
}
