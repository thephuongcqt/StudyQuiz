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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.DetailSubjectActivity;
import com.phuongnt.studyquiz.activity.SearchActivity;
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
    private Button btnLoadMore = null;
    private LinearLayout llLoadMore;
    private TextView tvTitle;
    private SearchAdapter<SearchSubjectResponse> mAdapter;
    private SearchActivity.IButtonLoadMoreListener iButtonLoadMoreListener;

    public void setiButtonLoadMoreListener(SearchActivity.IButtonLoadMoreListener iButtonLoadMoreListener) {
        this.iButtonLoadMoreListener = iButtonLoadMoreListener;
    }

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
        llLoadMore = (LinearLayout) inflater.inflate(R.layout.button_load_more, null);
        btnLoadMore = (Button) llLoadMore.findViewById(R.id.btn_load_more);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iButtonLoadMoreListener.onButtonLoadMoreSelected();
            }
        });
        listView.addFooterView(llLoadMore);
        return rootView;
    }

    public void setupListView(List<SearchSubjectResponse> list){
        srcList = list;
        if(mAdapter == null){
            mAdapter = new SearchAdapter<>(list, getActivity());
            listView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }
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
            btnLoadMore.setVisibility(View.VISIBLE);
        } else{
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("No Chapter found");
            btnLoadMore.setVisibility(View.GONE);
        }
    }
}
