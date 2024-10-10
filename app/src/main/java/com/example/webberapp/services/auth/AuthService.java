package com.example.webberapp.services.auth;

import com.example.webberapp.pojo.AuthTokens;
import com.example.webberapp.services.ApiClient;
import com.example.webberapp.utils.CustomExceptionHandler;

import java.io.IOException;

import retrofit2.Call;

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

    public AuthTokens login(String username, String password) throws InterruptedException {
        LoginReqDto req = new LoginReqDto(username, password);
        final LoginResDto[] res = new LoginResDto[1];
        Call<LoginResDto> call = authApiInterface.login(req);

        Thread t = new Thread(() -> {
            try {
                res[0] = call.execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        t.setUncaughtExceptionHandler(new CustomExceptionHandler());
        t.start();
        t.join();

        if (res[0] == null) return null;
        return new AuthTokens(res[0].accessToken, res[0].accessTokenExpiration, res[0].refreshToken, res[0].refreshTokenExpiration);
    }
}
