package com.phuongnt.studyquiz.model.apimodel.loginservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/16/17.
 */

public class LoginRequest implements Serializable {
    @SerializedName("Username")
    public String username;
    @SerializedName("Password")
    public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
