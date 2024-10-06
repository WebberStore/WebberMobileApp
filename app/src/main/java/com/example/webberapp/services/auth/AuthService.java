package com.example.webberapp.services.auth;

import androidx.annotation.NonNull;

import com.example.webberapp.pojo.AuthTokens;
import com.example.webberapp.services.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService {
    private static AuthService authService;
    private final AuthApiInterface authApiInterface;

    private AuthService() {
        this.authApiInterface = ApiClient.getClient().create(AuthApiInterface.class);
    }

    public static AuthService getService() {
        if (authService == null) authService = new AuthService();
        return authService;
    }

    public AuthTokens login(String username, String password) {
        LoginReqDto req = new LoginReqDto(username, password);
        LoginResDto res;
        Call<LoginResDto> call = authApiInterface.login(req);


        Callback<LoginResDto> callback = new Callback<LoginResDto>() {
            @Override
            public void onResponse(@NonNull Call<LoginResDto> call, @NonNull Response<LoginResDto> response) {

            }

            @Override
            public void onFailure(@NonNull Call<LoginResDto> call, @NonNull Throwable throwable) {

            }
        };
        call.enqueue(callback);
        try {
            res = call.execute().body();
        } catch (IOException e) {
            return null;
        }
        if (res == null) return null;
        return new AuthTokens(res.accessToken, res.accessTokenExpiration, res.refreshToken, res.refreshTokenExpiration);
    }
}
