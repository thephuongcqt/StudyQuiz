package com.phuongnt.studyquiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.SearchActivity;
import com.phuongnt.studyquiz.database.SearchHistoryDB;
import com.phuongnt.studyquiz.model.viewmodel.SearchHistory;
import com.phuongnt.studyquiz.model.viewmodel.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private List<SearchHistory> histories;
    private List<String> srcHistories = new ArrayList<>();
    private User user;
    private ListView lvSearch;
    private ArrayAdapter<String> mAdapter;
    public SecondFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        lvSearch = (ListView) rootView.findViewById(R.id.lv_search);
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String searchValue = srcHistories.get(position);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(AppConst.SEARCH_VALUE, searchValue);
                startActivity(intent);
            }
        });
        updateList();
        return rootView;
    }

    public void updateList(){
        if(getActivity() == null){
            return;
        }

        if(user == null){
            user = User.getCurrentUser();
        }
        histories = new SearchHistoryDB().getUserSearchHistory(user.getUserId());
        if(histories.size() == srcHistories.size()){
            return;
        }
        refreshSouceHistories();
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, srcHistories);
        lvSearch.setAdapter(mAdapter);
    }

    private void refreshSouceHistories(){
        srcHistories = new ArrayList<>();
        for(SearchHistory item : histories){
            srcHistories.add(item.getSearchValue());
        }
    }

}
