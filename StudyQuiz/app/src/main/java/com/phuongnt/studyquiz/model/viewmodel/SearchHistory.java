package com.phuongnt.studyquiz.model.viewmodel;

import android.util.Log;

import com.phuongnt.studyquiz.utils.MyDateFormater;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by PhuongNT on 10/28/17.
 */

public class SearchHistory implements Serializable {
    private long id;
    private String searchValue;
    private long userId;
    private Date date;

    public SearchHistory() {
    }

    public SearchHistory(String searchValue, long userId, Date date) {
        this.searchValue = searchValue;
        this.userId = userId;
        this.date = date;
    }

    public SearchHistory(long id, String searchValue, long userId, Date date) {
        this.id = id;
        this.searchValue = searchValue;
        this.userId = userId;
        this.date = date;
    }

    public SearchHistory(long id, String searchValue, long userId, String dateStr) {
        this.id = id;
        this.searchValue = searchValue;
        this.userId = userId;
        Date date = null;
        try{
            date = MyDateFormater.getDateFromString(dateStr);
        } catch(ParseException e){
            Log.e("SearchHistory", e.getMessage());
        }
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
