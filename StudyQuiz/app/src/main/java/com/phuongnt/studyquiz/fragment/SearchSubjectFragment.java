package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSubjectFragment extends Fragment {
    private ListView listView = null;
    private List<SearchSubjectResponse> srcList;
    private  Button btnLoadMore = null;

    public SearchSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_subject, container, false);
        listView = (ListView) rootView.findViewById(R.id.lv_subject);
        btnLoadMore = (Button) rootView.findViewById(R.id.btn_load_more);
        return rootView;
    }

    public void setupListView(List<SearchSubjectResponse> list){
        srcList = list;
        SearchAdapter<SearchSubjectResponse> adapter = new SearchAdapter<>(list, getActivity());
        listView.setAdapter(adapter);
    }
}
