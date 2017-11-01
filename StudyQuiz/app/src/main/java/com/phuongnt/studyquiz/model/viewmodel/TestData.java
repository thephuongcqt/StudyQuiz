package com.phuongnt.studyquiz.model.viewmodel;

import android.util.Log;

import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;
import com.phuongnt.studyquiz.model.apimodel.studiedquestionservice.StudiedQuestions;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PhuongNT on 10/24/17.
 */

public class TestData {
    private TestData(){

    }

    private static List<Question> questions = null;

    public static void setQuestions(List<Question> questions) {
        TestData.questions = questions;
    }

    public static List<Question> getQuestions() {
        return questions;

    }

    public static void addQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<>();
        }
        questions.add(question);
    }

    public static void addQuestion(QuestionResponse question){
        if(questions == null){
            questions = new ArrayList<>();
        }
        questions.add(new Question(question));
    }

    public static void resetAnswer(){
        for(Question item : questions){
            item.setSelectedAnswer(null);
        }
    }

    public static void checkAnswer(){
        for(Question item : questions){
            if(item.getSelectedAnswer() != null){
                int correctAnswer = Integer.parseInt(item.getValue().getDefinition());
                if(correctAnswer == item.getSelectedAnswer()){
                    item.setCorrect(true);
                } else{
                    item.setCorrect(false);
                }
            } else{
                item.setCorrect(false);
            }
        }
    }

    public static void saveStudiedQuestions(){
        User user = User.getCurrentUser();
        if(user == null || questions == null || questions.isEmpty()){
            return;
        }
        checkAnswer();
        Map<Long, Boolean> studiedQuestions = new HashMap<>();
        for(Question item : questions){
            studiedQuestions.put(item.getValue().getQuestionId(), item.isCorrect());
        }

        StudiedQuestions request = new StudiedQuestions(user.getUserId(), studiedQuestions);

        IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
        Call<CommonResponse> call = iapiHelper.saveStudiedQuestions(request);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    CommonResponse commonResponse = response.body();
                    if(commonResponse.isSuccess()){
                        // save success
                    } else{
                        Log.e("saveStudiedQuestions", commonResponse.getError());
                    }
                }else{
                    Log.e("saveStudiedQuestions", response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.e("saveStudiedQuestions", t.getMessage());
            }
        });
    }
}
