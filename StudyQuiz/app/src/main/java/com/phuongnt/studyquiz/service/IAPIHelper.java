package com.phuongnt.studyquiz.service;

import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginRequest;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.model.apimodel.questionservice.QuestionResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by PhuongNT on 10/16/17.
 */

public interface IAPIHelper {
    @POST("User/PostLogin")
    Call<CommonResponse<LoginResponse>> login(@Body LoginRequest loginRequest);
    @GET("Search/GetChapter")
    Call<CommonResponse<List<SearchChapterResponse>>> searchChapter(@QueryMap Map<String, String> params);
    @GET("Search/GetSubject")
    Call<CommonResponse<List<SearchSubjectResponse>>> searchSubject(@QueryMap Map<String, String> params);
    @GET("Question/GetQuestionForSubjectTest")
    Call<CommonResponse<List<QuestionResponse>>> getSubjectTest(@QueryMap Map<String, String> params);
    @GET("Question/GetQuestionForChapterTest")
    Call<CommonResponse<List<QuestionResponse>>> getChapterTest(@QueryMap Map<String, String> params);
    @GET("Question/GetQuestionForSubjectFlashCard")
    Call<CommonResponse<List<QuestionResponse>>> getSubjectCards(@QueryMap Map<String, String> params);
    @GET("Question/GetQuestionForChapterFlashCard")
    Call<CommonResponse<List<QuestionResponse>>> getChapterCards(@QueryMap Map<String, String> params);
}
