package com.example.webberapp.services;

import retrofit2.Retrofit;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            String baseUrl = "http://10.98.70.14.162:5230/";
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        }
        return retrofit;
    }
}
