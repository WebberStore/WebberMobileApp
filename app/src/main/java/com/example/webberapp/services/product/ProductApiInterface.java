package com.example.webberapp.services.product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApiInterface {
    @GET("/api/Products?page=1&pageSize=10")
    Call<GetProductsResDto> getProducts();

    @GET("/api/Products/category/{categoryId}?page=1&pageSize=10")
    Call<GetProductsResDto> getProductsByCategory(@Path("categoryId") int categoryId);

    @GET("/api/Products/search")
    Call<GetProductsResDto> getProductsBySearch(@Query("searchTerm") String searchTerm);

    @GET("/api/Products/search")
    Call<GetProductsResDto> getProductsBySearch(@Query("searchTerm") String searchTerm, @Query("categoryId") int categoryId);
}
