package com.phuongnt.studyquiz.model.apimodel.loginservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PhuongNT on 10/16/17.
 */

public class LoginResponse implements Serializable {
    @SerializedName("Feedbacks")
    private
    List feedbacks;
    @SerializedName("MonthlyReports")
    private
    List monthlyReports;
    @SerializedName("StudiedQuestions")
    private
    List studiedQuestions;
    @SerializedName("UserId")
    private
    long userId;
    @SerializedName("Username")
    private
    String username;
    @SerializedName("Password")
    private
    String password;
    @SerializedName("Email")
    private
    String email;
    @SerializedName("Name")
    private
    String name;
    @SerializedName("Role")
    private
    long role;

    public List getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List getMonthlyReports() {
        return monthlyReports;
    }

    public void setMonthlyReports(List monthlyReports) {
        this.monthlyReports = monthlyReports;
    }

    public List getStudiedQuestions() {
        return studiedQuestions;
    }

    public void setStudiedQuestions(List studiedQuestions) {
        this.studiedQuestions = studiedQuestions;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }
}
