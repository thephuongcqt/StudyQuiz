package com.phuongnt.studyquiz.model.apimodel.questionservice;

import com.google.gson.annotations.SerializedName;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by PhuongNT on 10/23/17.
 */

public class QuestionResponse implements Serializable {
    @SerializedName("QuestionId")
    private long questionId;
    @SerializedName("TypeId")
    private int typeId;
    @SerializedName("Term")
    private String term;
    @SerializedName("Definition")
    private String definition;
    @SerializedName("CreatedDate")
    private Date createdDate;
    @SerializedName("ChapterId")
    private long chapterId;
    @SerializedName("CreatedUser")
    private long createdUser;
    @SerializedName("IsEnable")
    private boolean isEnable;
    @SerializedName("Chapter")
    private SearchChapterResponse chapter;
    @SerializedName("Feedbacks")
    private List feedbacks;
    @SerializedName("StudiedQuestions")
    private List studiedQuestions;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(long createdUser) {
        this.createdUser = createdUser;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public SearchChapterResponse getChapter() {
        return chapter;
    }

    public void setChapter(SearchChapterResponse chapter) {
        this.chapter = chapter;
    }

    public List getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List getStudiedQuestions() {
        return studiedQuestions;
    }

    public void setStudiedQuestions(List studiedQuestions) {
        this.studiedQuestions = studiedQuestions;
    }
}
