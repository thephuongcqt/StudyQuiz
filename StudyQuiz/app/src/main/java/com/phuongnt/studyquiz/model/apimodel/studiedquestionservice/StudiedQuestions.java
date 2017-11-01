package com.phuongnt.studyquiz.model.apimodel.studiedquestionservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by PhuongNT on 11/1/17.
 */

public class StudiedQuestions implements Serializable {
    @SerializedName("UserId")
    private long userId;
    @SerializedName("Questions")
    private Map<Long, Boolean> questions;

    public StudiedQuestions(long userId, Map<Long, Boolean> questions) {
        this.userId = userId;
        this.questions = questions;
    }

    public StudiedQuestions(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map<Long, Boolean> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Long, Boolean> questions) {
        this.questions = questions;
    }
}
