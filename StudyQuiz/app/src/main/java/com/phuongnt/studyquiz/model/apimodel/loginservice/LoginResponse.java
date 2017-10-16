package com.phuongnt.studyquiz.model.apimodel.loginservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PhuongNT on 10/16/17.
 */

public class LoginResponse implements Serializable {
    @SerializedName("Feedbacks")
    List feedbacks;
    @SerializedName("MonthlyReports")
    List monthlyReports;
    @SerializedName("StudiedQuestions")
    List studiedQuestions;
    @SerializedName("UserId")
    long userId;
    @SerializedName("Username")
    String username;
    @SerializedName("Password")
    String password;
    @SerializedName("Email")
    String email;
    @SerializedName("Name")
    String name;
    @SerializedName("Role")
    long role;
}
