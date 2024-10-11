package com.example.webberapp.services.category;

import com.example.webberapp.pojo.Category;
import com.example.webberapp.services.ApiClient;
import com.example.webberapp.utils.CustomExceptionHandler;

import java.io.IOException;

import retrofit2.Call;

public class CategoryService {
    private static CategoryService categoryService;

    private final CategoryApiInterface categoryApiInterface;

    private CategoryService() {
        this.categoryApiInterface = ApiClient.getClient().create(CategoryApiInterface.class);
    }

    public static CategoryService getService() {
        if (categoryService == null) categoryService = new CategoryService();
        return categoryService;
    }

    public Category[] getCategories()  {
        Call<Category[]> call = this.categoryApiInterface.getCategories();
        Category[][] res = new Category[1][];
        Thread t = new Thread(() -> {
            try {
                res[0] = call.execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        t.setUncaughtExceptionHandler(new CustomExceptionHandler());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            new CustomExceptionHandler().logger(e);
        }

        if (res[0] == null) return new Category[0];
        return res[0];
    }
}
