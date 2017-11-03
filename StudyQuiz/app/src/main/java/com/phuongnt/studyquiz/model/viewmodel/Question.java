package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuongNT on 10/24/17.
 */

public class Question implements Serializable{
    private QuestionResponse value;
    private Boolean correct;
    private Integer selectedAnswer;

    public Question() {
    }

    public Question(QuestionResponse value) {
        this.value = value;
        this.correct = null;
        this.selectedAnswer = null;
    }

    public Question(QuestionResponse value, boolean correct) {
        this.value = value;
        this.correct = correct;
    }

    public QuestionResponse getValue() {
        return value;
    }

    public void setValue(QuestionResponse value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Integer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Integer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getRealTerm(){
        if(this.value == null || this.value.getTerm().isEmpty()){
            return "";
        }
        String term = this.value.getTerm();
        String[] tokens = term.split("\\|");
        if(tokens.length > 0){
            return tokens[0].trim();
        }
        return "";
    }

    public List<String> getAnswers(){
        if(this.value == null || this.value.getTerm().isEmpty()){
            return null;
        }
        String term = this.value.getTerm();
        String[] tokens = term.split("\\|");
        List<String> answers = new ArrayList<>();
        if(tokens.length > 1){
            for(int i = 1; i < tokens.length; i++){
                answers.add(tokens[i].trim());
            }
        }
        return answers;
    }
}
