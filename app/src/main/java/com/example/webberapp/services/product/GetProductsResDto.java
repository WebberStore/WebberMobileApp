package com.example.webberapp.services.product;

import com.example.webberapp.pojo.Product;
import com.google.gson.annotations.SerializedName;

public class GetProductsResDto {
    @SerializedName("items")
    public Product[] items;
}
