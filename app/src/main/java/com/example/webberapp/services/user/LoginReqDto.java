package com.example.webberapp.services.user;

import com.google.gson.annotations.SerializedName;

public class LoginReqDto {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    public LoginReqDto(String username, String password) {
        this.password = password;
        this.username = username;
    }
}
