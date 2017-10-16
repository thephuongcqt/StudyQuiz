package com.phuongnt.studyquiz.model.apimodel;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/16/17.
 */

public class CommonResponse<T> implements Serializable {
    private boolean success;
    private T value;
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
