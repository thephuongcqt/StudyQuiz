package com.phuongnt.studyquiz.model.viewmodel;

import com.google.gson.annotations.SerializedName;
import com.phuongnt.studyquiz.database.UserDB;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/17/17.
 */

public class User implements Serializable {

    private long userId;
    private String username;
    private String password;
    private String email;
    private String name;
    private long role;

    public User() {
    }

    private static User _instance = null;

    public static User getCurrentUser(){
        if(_instance == null){
            _instance = new UserDB().getCurrentUser();
        }
        return _instance;
    }

    public User(long userId, String username, String password, String email, String name, long role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
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
