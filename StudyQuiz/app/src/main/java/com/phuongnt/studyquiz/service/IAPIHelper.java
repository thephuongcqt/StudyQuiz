package com.phuongnt.studyquiz.service;

import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginRequest;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by PhuongNT on 10/16/17.
 */

public interface IAPIHelper {
    @POST("User/PostLogin")
    Call<CommonResponse<LoginResponse>> login(@Body LoginRequest loginRequest);
    @GET("Search/GetChapter")
    Call<CommonResponse<List<SearchChapterResponse>>> searchChapter(@Query("searchValue") String searchValue);
    @GET("Search/GetSubject")
    Call<CommonResponse<List<SearchSubjectResponse>>> searchSubject(@Query("searchValue") String searchValue);
}
