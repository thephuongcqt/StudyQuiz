package com.phuongnt.studyquiz.model.apimodel.signupservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class SignUpRequest implements Serializable {
    @SerializedName("Username")
    private String username;

    public SignUpRequest(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    @SerializedName("Password")
    private String password;
    @SerializedName("Email")
    private String email;
    @SerializedName("Name")
    private String name;

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
}
