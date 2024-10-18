package com.example.webberapp.services.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface UserApiInterface {
    @POST("/api/User/login")
    Call<LoginResDto> login(@Body LoginReqDto req);
}
