package com.example.webberapp.ui.browse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webberapp.databinding.FragmentBrowseBinding;
import com.example.webberapp.pojo.Product;
import com.example.webberapp.services.product.ProductService;
import com.example.webberapp.utils.CustomExceptionHandler;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BrowseViewModel browseViewModel = new ViewModelProvider(this).get(BrowseViewModel.class);

        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // fetch products
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

        RecyclerView recyclerView = binding.recyclerViewProducts;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(getActivity(), products);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}