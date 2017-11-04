package com.phuongnt.studyquiz.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.DetailSubjectActivity;
import com.phuongnt.studyquiz.adapter.SearchAdapter;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.RequestParam;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {
    private Button btnLoadData;
    private ListView lvSubjects;
    private SearchAdapter<SearchSubjectResponse> mAdapter;
    private List<SearchSubjectResponse> srcList;
    private Button btnLoadMore;
    private LinearLayout llLoadMore;
    private long offset = 0;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subject, container, false);
        btnLoadData = (Button) rootView.findViewById(R.id.btn_load_data);
        lvSubjects = (ListView) rootView.findViewById(R.id.lv_subjects);
        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLoadDataClick(v);
            }
        });

        llLoadMore = (LinearLayout) inflater.inflate(R.layout.button_load_more, null);
        btnLoadMore = (Button) llLoadMore.findViewById(R.id.btn_load_more);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLoadMoreClick(v);
            }
        });
        lvSubjects.addFooterView(llLoadMore);
        if(mAdapter != null){
            lvSubjects.setAdapter(mAdapter);
        }
        if(srcList != null && !srcList.isEmpty()){
            btnLoadMore.setVisibility(View.VISIBLE);
        } else{
            btnLoadMore.setVisibility(View.GONE);
        }
        updateListView();
        lvSubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchSubjectResponse selectedSubject = srcList.get(position);
                Intent intent = new Intent(getActivity(), DetailSubjectActivity.class);
                intent.putExtra(AppConst.KEY_SUBJECT_OBJ, selectedSubject);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void onButtonLoadMoreClick(View v){
        fetchData();
    }

    private void onButtonLoadDataClick(View v){
        fetchData();
    }

    private void fetchData(){
        try{
            MyProgressBar.show(getActivity());

            Map<String, String> params = new HashMap<>();
            params.put(RequestParam.SEARCH_OFFSET, offset + "");
            params.put(RequestParam.SEARCH_NUMBER, AppConst.SEARCH_ITEMS_NUMBER + "");

            IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
            Call<CommonResponse<List<SearchSubjectResponse>>> call = iapiHelper.fetchSubject(params);
            call.enqueue(new Callback<CommonResponse<List<SearchSubjectResponse>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<SearchSubjectResponse>>> call, Response<CommonResponse<List<SearchSubjectResponse>>> response) {
                    if(response.isSuccessful()){
                        CommonResponse<List<SearchSubjectResponse>> commonResponse = response.body();
                        if(commonResponse.isSuccess()){
                            onSuccess(commonResponse.getValue());
                        } else{
                            onError(commonResponse.getError());
                        }
                    } else{
                        onError(AppConst.ERROR_CONNECTION);
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse<List<SearchSubjectResponse>>> call, Throwable t) {
                    onError(t.getMessage());
                }
            });
        }catch (Exception e){
            Log.e("SubjectFragment", e.getMessage());
        }
    }

    private void onSuccess(List<SearchSubjectResponse> subjectsResponse){
        MyProgressBar.dismiss();
        if(srcList == null){
            srcList = new ArrayList<>();
        }
        if(subjectsResponse == null || subjectsResponse.isEmpty()){
            btnLoadMore.setVisibility(View.GONE);
        } else{
            offset = srcList.size();
            srcList.addAll(subjectsResponse);
            updateListView();
            btnLoadMore.setVisibility(View.VISIBLE);
        }
    }

    private void onError(String msg){
        MyProgressBar.dismiss();
        Log.e("onButtonLoadDataClick", msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void updateListView(){
        if(srcList == null){
            srcList = new ArrayList<>();
        }
        if(mAdapter == null){
            mAdapter = new SearchAdapter<>(srcList, getActivity());
            lvSubjects.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }

        if(srcList == null || srcList.isEmpty()){
            btnLoadData.setVisibility(View.VISIBLE);
        } else{
            btnLoadData.setVisibility(View.GONE);
        }
    }
}
