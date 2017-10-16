package com.phuongnt.studyquiz.service;

import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginRequest;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by PhuongNT on 10/16/17.
 */

public interface IAPIHelper {
    @POST("User/PostLogin")
    Call<CommonResponse<LoginResponse>> login(@Body LoginRequest loginRequest);
}
