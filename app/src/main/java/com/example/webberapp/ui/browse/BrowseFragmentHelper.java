package com.example.webberapp.ui.browse;

import android.util.Log;

import com.example.webberapp.pojo.Category;
import com.example.webberapp.pojo.Product;
import com.example.webberapp.services.category.CategoryService;
import com.example.webberapp.services.product.ProductService;
import com.example.webberapp.utils.CustomExceptionHandler;

public class BrowseFragmentHelper {
    public Product[] getProducts() {
        Product[] products = new Product[0];
        try {
            products = ProductService.getService().getProducts();
        } catch (InterruptedException e) {
            new CustomExceptionHandler().logger(e);
        }
        Log.d("__LOG", "Number of products fetched: " + products.length);
        for (int i = 0; i < products.length; i++) {
            Log.d("__LOG", "Product " + i + " name:" + products[i].name);
        }
        return products;
    }

    public Category[] getCategories() {
        Category[] categories = new Category[0];
        try {
            categories = CategoryService.getService().getCategories();
        } catch (InterruptedException e) {
            new CustomExceptionHandler().logger(e);
        }
        Log.d("__LOG", "Number of categories fetched: " + categories.length);
        for (int i = 0; i < categories.length; i++) {
            Log.d("__LOG", "Category " + i + " name:" + categories[i].name);
        }
        return categories;
    }
}
