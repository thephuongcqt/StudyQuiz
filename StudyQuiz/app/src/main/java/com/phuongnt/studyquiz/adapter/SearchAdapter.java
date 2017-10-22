package com.phuongnt.studyquiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.fragment.SearchChapterFragment;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.util.List;

/**
 * Created by PhuongNT on 10/22/17.
 */

public class SearchAdapter<T> extends BaseAdapter {
    private List<T> srcList;
    private LayoutInflater layoutInflater;
    private Context context;
    private IButtonClickListener buttonClickListener;

    public SearchAdapter(List<T> srcList, Context context, IButtonClickListener buttonClickListener) {
        this.srcList = srcList;
        this.context = context;
        this.buttonClickListener = buttonClickListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return srcList.size();
    }

    @Override
    public T getItem(int position) {
        return srcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        T item = srcList.get(position);
        if(item instanceof SearchChapterResponse){
            ((SearchChapterResponse) item).getChapterId();

        } else if(item instanceof SearchSubjectResponse){
            ((SearchSubjectResponse) item).getSubjectId();
        }
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.search_item, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            holder.tvSubItem = (TextView)convertView.findViewById(R.id.tv_sub_item);
            Button button = (Button)convertView.findViewById(R.id.btn_sub_item);
            button.setTag(position);
            holder.btnSubitem = button;
            button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    View parentRow = (View) v.getParent();
//                    LinearLayout linearLayout = (LinearLayout) parentRow.getParent();
//                    ListView listView = (ListView) linearLayout.getParent();
//                    final int positionOther = listView.getPositionForView(parentRow);
                    int position = (int)v.getTag();
                    buttonClickListener.buttonClickAt(position);
                }
            });
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        final T item = getItem(position);
        if(item instanceof SearchChapterResponse){
            SearchChapterResponse chapter = (SearchChapterResponse) item;
            holder.tvTitle.setText("Chapter: " + chapter.getName());
            holder.tvSubItem.setText("Subject: " + chapter.getSubject().getName());
            holder.btnSubitem.setText("See all chapter in this subject");
        } else if(item instanceof SearchSubjectResponse){
            SearchSubjectResponse subject = (SearchSubjectResponse)item;
            holder.tvTitle.setText("Subject: " + subject.getName());
            holder.tvSubItem.setText("Chapter Items: " + (subject.getChapters() == null ? 0 : subject.getChapters().size()));
            holder.btnSubitem.setText("See all Chapter items");
        }
        return convertView;
    }

    static class ViewHolder{
        TextView tvTitle;
        TextView tvSubItem;
        Button btnSubitem;
    }

    public interface IButtonClickListener{
        void buttonClickAt(int position);
    }
}
