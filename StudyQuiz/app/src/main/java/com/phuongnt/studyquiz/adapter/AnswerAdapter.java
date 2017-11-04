package com.phuongnt.studyquiz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.activity.ReviewAnswersActivity;
import com.phuongnt.studyquiz.model.viewmodel.Question;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by PhuongNT on 10/27/17.
 */

public class AnswerAdapter extends BaseAdapter {

    private List<Question> srcList;
    private LayoutInflater layoutInflater;
    private Context context;
    private ReviewAnswersActivity.IClickListener ivClickListener;
    public AnswerAdapter(List<Question> srcList, Context context, ReviewAnswersActivity.IClickListener ivClickListener) {
        this.srcList = srcList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.ivClickListener = ivClickListener;
    }

    @Override
    public int getCount() {
        return srcList.size();
    }

    @Override
    public Question getItem(int position) {
        return srcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return srcList.get(position).getValue().getQuestionId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tvQuestion;
        TextView tvCorrectAnswer;
        LinearLayout llWrongAnswer;
        TextView tvWrongAnswer;
        LinearLayout llFooter;
        LinearLayout llIcon;
        TextView tvResult;
        ImageView ivFeedback;

        Question question = getItem(position);

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.question_result_item, parent, false);
        }
        //binding control
        tvQuestion = (TextView)convertView.findViewById(R.id.tv_question);
        tvCorrectAnswer = (TextView) convertView.findViewById(R.id.tv_correct_answer);
        llWrongAnswer = (LinearLayout) convertView.findViewById(R.id.ll_wrong_answer);
        tvWrongAnswer = (TextView) convertView.findViewById(R.id.tv_wrong_answer);
        llFooter = (LinearLayout) convertView.findViewById(R.id.ll_footer);
        llIcon = (LinearLayout) convertView.findViewById(R.id.ll_icon_result);
        tvResult = (TextView) convertView.findViewById(R.id.tv_result);
        ivFeedback = (ImageView) convertView.findViewById(R.id.icon_feedback);
        //binding control

        //setup footer
        if(question.isCorrect()){
            llFooter.setBackgroundResource(R.color.colorPrimary);
            tvResult.setText("Correct");
            llIcon.setBackgroundResource(R.drawable.ic_correct_white);
            llWrongAnswer.setVisibility(View.GONE);
        } else{
            llWrongAnswer.setVisibility(View.VISIBLE);
            tvResult.setText("Incorrect");
            llFooter.setBackgroundResource(R.color.light_red);
            llIcon.setBackgroundResource(R.drawable.ic_incorrect_white);
        }
        //setup footer

        int correctAnswer = Integer.parseInt(question.getValue().getDefinition());
        if(question.getValue().getTypeId() == 1){
            //True false
            tvQuestion.setText(getItem(position).getValue().getTerm());
            if(correctAnswer == 0){
                tvCorrectAnswer.setText("False");
                tvWrongAnswer.setText("True");
            } else{
                tvCorrectAnswer.setText("True");
                tvWrongAnswer.setText("False");
            }
        } else{
            // Multiple Choice
            tvQuestion.setText(question.getRealTerm());
            List<String> answers = question.getAnswers();
            if(question.getSelectedAnswer() != null){
                tvWrongAnswer.setText(answers.get(question.getSelectedAnswer()));
            }
            tvCorrectAnswer.setText(answers.get(correctAnswer));
        }
        tvWrongAnswer.setTextColor(Color.parseColor(AppConst.COLOR_RED));
        if(question.getSelectedAnswer() == null){
            tvWrongAnswer.setText(R.string.review_answer_not_selected);
            tvWrongAnswer.setTextColor(Color.parseColor(AppConst.COLOR_GRAY));
        }

        ivFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivClickListener.onImageFeedbackSelected(srcList.get(position));
            }
        });

        convertView.getLayoutParams().height = parent.getHeight();
        convertView.requestLayout();
        return convertView;
    }
}
