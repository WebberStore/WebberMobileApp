package com.example.webberapp.services.category;

import com.example.webberapp.pojo.Category;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApiInterface {
    @GET("/api/Categories")
    Call<Category[]> getCategories();
}
