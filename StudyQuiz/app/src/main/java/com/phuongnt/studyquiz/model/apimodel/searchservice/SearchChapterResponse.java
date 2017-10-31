package com.phuongnt.studyquiz.model.apimodel.searchservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by PhuongNT on 10/18/17.
 */

public class SearchChapterResponse implements Serializable {
    @SerializedName("Subject")
    private
    SearchSubjectResponse subject;
    @SerializedName("Questions")
    private
    List questions;
    @SerializedName("ChapterId")
    private
    long chapterId;
    @SerializedName("Name")
    private
    String name;
    @SerializedName("CreatedDate")
    private
    Date createdDate;
    @SerializedName("SubjectId")
    private
    long subjectId;

    public SearchChapterResponse() {
    }

    public SearchChapterResponse(SearchSubjectResponse subject, List questions, long chapterId, String name, Date createdDate, long subjectId) {
        this.subject = subject;
        this.questions = questions;
        this.chapterId = chapterId;
        this.name = name;
        this.createdDate = createdDate;
        this.subjectId = subjectId;
    }

    public SearchChapterResponse(long chapterId, String name, Date createdDate, long subjectId) {
        this.chapterId = chapterId;
        this.name = name;
        this.createdDate = createdDate;
        this.subjectId = subjectId;
    }

    public SearchSubjectResponse getSubject() {
        return subject;
    }

    public void setSubject(SearchSubjectResponse subject) {
        this.subject = subject;
    }

    public List getQuestions() {
        return questions;
    }

    public void setQuestions(List questions) {
        this.questions = questions;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }
}
