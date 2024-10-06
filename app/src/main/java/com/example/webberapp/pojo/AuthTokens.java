package com.example.webberapp.pojo;

public class AuthTokens {
    public String accessToken;
    public String accessTokenExpiration;
    public String refreshToken;
    public String refreshTokenExpiration;

    public AuthTokens() {
    }

    public AuthTokens(String accessToken, String accessTokenExpiration, String refreshToken, String refreshTokenExpiration) {
        this.accessToken = accessToken;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}
