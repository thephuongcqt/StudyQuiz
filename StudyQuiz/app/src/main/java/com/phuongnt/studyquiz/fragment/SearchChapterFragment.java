package com.phuongnt.studyquiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.DetailChapterActivity;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChapterFragment extends Fragment{
    private ListView listView = null;
    private List<SearchChapterResponse> srcList;
    private TextView tvTitle;

    public SearchChapterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_chapter, container, false);
        listView = (ListView) rootView.findViewById(R.id.lv_chapter);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_fragment_title);
        return rootView;
    }

    public void setupListView(List<SearchChapterResponse> list){
        srcList = list;
        SearchAdapter<SearchChapterResponse> adapter = new SearchAdapter<>(list, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchChapterResponse item = srcList.get(position);
                Intent intent = new Intent(getActivity(), DetailChapterActivity.class);
                intent.putExtra(AppConst.KEY_CHAPTER_OBJ, item);
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
