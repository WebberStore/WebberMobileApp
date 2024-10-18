package com.example.webberapp.services.product;

import com.example.webberapp.pojo.Category;
import com.example.webberapp.pojo.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApiInterface {
    @GET("/api/Products/category")
    Call<Category[]> getCategories();

    @GET("/api/Products")
    Call<Product[]> getProducts();

    @GET("/api/Products/getby/category/{categoryId}")
    Call<Product[]> getProductsByCategory(@Path("categoryId") String categoryId);

    @GET("/api/Products/search")
    Call<Product[]> getProductsBySearch(@Query("name") String searchTerm);

    @GET("/api/Products/search")
    Call<Product[]> getProductsBySearch(@Query("searchTerm") String searchTerm, @Query("categoryId") String categoryId);
}
