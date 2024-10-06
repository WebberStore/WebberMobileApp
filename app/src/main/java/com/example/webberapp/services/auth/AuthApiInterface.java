package com.example.webberapp.services.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface AuthApiInterface {
    @POST("/api/Authentication/login")
    Call<LoginResDto> login(@Body LoginReqDto req);
}
