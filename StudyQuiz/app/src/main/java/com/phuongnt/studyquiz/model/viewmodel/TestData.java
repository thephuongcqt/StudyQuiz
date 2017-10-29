package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;

import java.util.ArrayList;
import java.util.List;

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
}
