package com.example.webberapp.services.product;

import com.example.webberapp.pojo.Category;
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

    public Category[] getCategories() {
        Call<Category[]> call = this.productApiInterface.getCategories();
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

    public Product[] getProducts() {
        Call<Product[]> call = this.productApiInterface.getProducts();
        Product[][] res = new Product[1][];
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

        if (res[0] == null) return new Product[0];
        return res[0];
    }

    public Product[] getProductsByCategory(String categoryId) {
        Call<Product[]> call = this.productApiInterface.getProductsByCategory(categoryId);
        Product[][] res = new Product[1][];

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

        if (res[0] == null) return new Product[0];
        return res[0];
    }

    public Product[] getProductsBySearch(String searchTerm) {
        Call<Product[]> call = this.productApiInterface.getProductsBySearch(searchTerm);
        Product[][] res = new Product[1][];

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

        if (res[0] == null) return new Product[0];
        return res[0];
    }

    public Product[] getProductsBySearch(String searchTerm, String categoryId) {
        Call<Product[]> call = this.productApiInterface.getProductsBySearch(searchTerm, categoryId);
        Product[][] res = new Product[1][];

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

        if (res[0] == null) return new Product[0];
        return res[0];
    }
}
