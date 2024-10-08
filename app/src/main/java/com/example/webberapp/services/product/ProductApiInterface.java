package com.example.webberapp.services.product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiInterface {
    @GET("/api/Products?page=1&pageSize=10")
    Call<GetProductsResDto> getProducts();
}
