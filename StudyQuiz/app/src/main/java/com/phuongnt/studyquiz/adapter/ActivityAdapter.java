package com.phuongnt.studyquiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.viewmodel.Activity;
import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.util.List;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class ActivityAdapter extends BaseAdapter {
    private List<Activity> srcList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ActivityAdapter(List<Activity> srcList, Context context) {
        this.srcList = srcList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return srcList.size();
    }

    @Override
    public Activity getItem(int position) {
        return srcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return srcList.get(position).getActivityId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvTitle;
        TextView tvSubtitle;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.activity_item, parent, false);
        }
        tvTitle = (TextView) convertView.findViewById(R.id.tv_activity_title);
        tvSubtitle = (TextView) convertView.findViewById(R.id.tv_activity_subtitle);
        Activity item = getItem(position);
        if(item.getChapterId() >= 0){
            tvTitle.setText("Chapter: " + item.getChapter().getName());
        } else{
            tvTitle.setText("Subject: " + item.getSubject().getName());
        }
        tvSubtitle.setText(MyDateFormater.getStringFromDate(item.getDate()));
        return convertView;
    }
}
