package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.database.ActivityDB;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class Activity implements Serializable {
    public static int TYPE_FLASH_CARD = 0;
    public static int TYPE_TEST = 1;
    private long activityId;
    private long chapterId;
    private long subjectId;
    private int type;
    private Date date;
    private long userId;

    private SearchChapterResponse chapter;
    private SearchSubjectResponse subject;

    public Activity(long activityId, long chapterId, long subjectId, int type, Date date, long userId) {
        this.activityId = activityId;
        this.chapterId = chapterId;
        this.subjectId = subjectId;
        this.type = type;
        this.date = date;
        this.userId = userId;
    }

    public Activity(long chapterId, long subjectId, int type, Date date, long userId) {
        this.chapterId = chapterId;
        this.subjectId = subjectId;
        this.type = type;
        this.date = date;
        this.userId = userId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public SearchChapterResponse getChapter() {
        return chapter;
    }

    public void setChapter(SearchChapterResponse chapter) {
        this.chapter = chapter;
    }

    public SearchSubjectResponse getSubject() {
        return subject;
    }

    public void setSubject(SearchSubjectResponse subject) {
        this.subject = subject;
    }

    public static List<Activity> getAll(){
        User user = User.getCurrentUser();
        if(user == null){
            return new ArrayList<>();
        }
        List<Activity> list = ActivityDB.getAll(user.getUserId());
        for(Activity item : list){
            if(item.getChapterId() >= 0){
                SearchChapterResponse chapter = Chapter.getChapterById(item.getChapterId());
                item.setChapter(chapter);
            } else if(item.getSubjectId() >= 0){
                SearchSubjectResponse subject = Subject.getSubjectById(item.getSubjectId());
                item.setSubject(subject);
            }
        }
        return list;
    }
}
