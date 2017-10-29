package com.phuongnt.studyquiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.DetailSubjectActivity;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSubjectFragment extends Fragment {
    private ListView listView = null;
    private List<SearchSubjectResponse> srcList;
    private  Button btnLoadMore = null;
    private TextView tvTitle;

    public SearchSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_subject, container, false);
        listView = (ListView) rootView.findViewById(R.id.lv_subject);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_fragment_title);
//        btnLoadMore = (Button) rootView.findViewById(R.id.btn_load_more);
        return rootView;
    }

    public void setupListView(List<SearchSubjectResponse> list){
        srcList = list;
        SearchAdapter<SearchSubjectResponse> adapter = new SearchAdapter<>(list, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchSubjectResponse item = srcList.get(position);
                Intent intent = new Intent(getActivity(), DetailSubjectActivity.class);
                intent.putExtra(AppConst.KEY_SUBJECT_OBJ, item);
                startActivity(intent);
            }
        });
        if(srcList.size() > 0){
            tvTitle.setVisibility(View.GONE);
        } else{
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("No Chapter found");
        }
    }
}
