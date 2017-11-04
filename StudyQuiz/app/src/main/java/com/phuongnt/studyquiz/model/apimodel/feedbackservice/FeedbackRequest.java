package com.phuongnt.studyquiz.model.apimodel.feedbackservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PhuongNT on 11/3/17.
 */

public class FeedbackRequest implements Serializable {
    @SerializedName("UserId")
    private long userId;
    @SerializedName("QuestionId")
    private long questionId;
    @SerializedName("ErrorId")
    private int errorId;
    @SerializedName("Comment")
    private String comment;
    @SerializedName("Accepted")
    private boolean accepted;

    public FeedbackRequest(long userId, long questionId, int errorId, String comment) {
        this.userId = userId;
        this.questionId = questionId;
        this.errorId = errorId;
        this.comment = comment;
        this.accepted = false;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
