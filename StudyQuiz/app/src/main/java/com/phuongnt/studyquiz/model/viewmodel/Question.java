package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;

/**
 * Created by PhuongNT on 10/24/17.
 */

public class Question {
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
}
