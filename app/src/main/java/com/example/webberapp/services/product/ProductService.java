package com.example.webberapp.services.product;

import com.example.webberapp.pojo.Product;
import com.example.webberapp.services.ApiClient;
import com.example.webberapp.utils.CustomExceptionHandler;

import java.io.IOException;

import retrofit2.Call;

public class ProductService {
    private static ProductService productService;
    private final ProductApiInterface productApiInterface;

    private ProductService() {
        this.productApiInterface = ApiClient.getClient().create(ProductApiInterface.class);
    }

    public static ProductService getService() {
        if (productService == null) productService = new ProductService();
        return productService;
    }

    public Product[] getProducts() throws InterruptedException {
        Call<GetProductsResDto> call = this.productApiInterface.getProducts();
        GetProductsResDto[] res = new GetProductsResDto[1];
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
        return res[0].items;
    }
}
