package com.phuongnt.studyquiz.model.viewmodel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class Activity implements Serializable {
    private long activityId;
    private Long chapterId;
    private Long subjectId;
    private int type;
    private Date date;
    private long userId;

    public Activity(long activityId, Long chapterId, Long subjectId, int type, Date date, long userId) {
        this.activityId = activityId;
        this.chapterId = chapterId;
        this.subjectId = subjectId;
        this.type = type;
        this.date = date;
        this.userId = userId;
    }

    public Activity(Long chapterId, Long subjectId, int type, Date date, long userId) {
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

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
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
}
