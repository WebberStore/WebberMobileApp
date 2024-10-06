package com.example.webberapp.services.auth;

import com.google.gson.annotations.SerializedName;

public class LoginResDto {
    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("accessTokenExpiration")
    public String accessTokenExpiration;
    @SerializedName("refreshToken")
    public String refreshToken;
    @SerializedName("refreshTokenExpiration")
    public String refreshTokenExpiration;
}
