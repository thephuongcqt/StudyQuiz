package com.phuongnt.studyquiz.model.apimodel.searchservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by PhuongNT on 10/18/17.
 */

public class SearchSubjectResponse implements Serializable {
    @SerializedName("Chapters")
    private
    List<SearchChapterResponse> chapters;
    @SerializedName("SubjectId")
    private
    long subjectId;
    @SerializedName("Name")
    private
    String name;
    @SerializedName("CreatedDate")
    private
    Date createdDate;

    public SearchSubjectResponse() {

    }

    public SearchSubjectResponse(List<SearchChapterResponse> chapters, long subjectId, String name, Date createdDate) {
        this.chapters = chapters;
        this.subjectId = subjectId;
        this.name = name;
        this.createdDate = createdDate;
    }

    public List<SearchChapterResponse> getChapters() {
        return chapters;
    }

    public void setChapters(List<SearchChapterResponse> chapters) {
        this.chapters = chapters;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
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
}
