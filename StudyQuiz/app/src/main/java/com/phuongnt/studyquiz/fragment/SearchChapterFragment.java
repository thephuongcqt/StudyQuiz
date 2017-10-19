package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.phuongnt.studyquiz.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChapterFragment extends Fragment {
    private ListView listView = null;

    public SearchChapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search_chapter, container, false);
        listView = (ListView) rootView.findViewById(R.id.lv_chapter);
        return rootView;
    }

    public void setupListView(List<String> list){
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.search_item, list);
        listView.setAdapter(adapter);
    }
}
