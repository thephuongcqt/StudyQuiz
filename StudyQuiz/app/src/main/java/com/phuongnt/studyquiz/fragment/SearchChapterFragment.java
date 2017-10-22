package com.phuongnt.studyquiz.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchChapterFragment extends Fragment{
    private ListView listView = null;
    private List<SearchChapterResponse> srcList;

    private SearchAdapter.IButtonClickListener buttonClickListener;

    public void setButtonClickListener(SearchAdapter.IButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

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

    public void setupListView(List<SearchChapterResponse> list){
        SearchAdapter<SearchChapterResponse> adapter = new SearchAdapter<>(list, getActivity(), buttonClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchChapterResponse item = srcList.get(position);
            }
        });
    }

    public void onButtonClick(SearchChapterResponse chapter){

    }
    public void onItemClick(SearchChapterResponse chapter){

    }
}
