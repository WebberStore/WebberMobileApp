package com.example.webberapp.ui.browse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webberapp.R;
import com.example.webberapp.databinding.FragmentBrowseBinding;
import com.example.webberapp.pojo.Category;
import com.example.webberapp.pojo.Product;
import com.example.webberapp.services.category.CategoryService;
import com.example.webberapp.services.product.ProductService;


public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;
    private Product[] products;
    Category[] categories;

    String searchString = ""; // "" = not searching anything
    int selectedCategory = 0; // 0 = not selected any category

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // fetch data
        products = ProductService.getService().getProducts();
        categories = CategoryService.getService().getCategories();

        if (products.length == 0) return root;

        // configure recycler view for products
        RecyclerView recyclerView = binding.recyclerViewProducts;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ProductRecyclerViewAdapter recyclerViewAdapter = new ProductRecyclerViewAdapter(requireContext(), products);
        recyclerView.setAdapter(recyclerViewAdapter);

        // configure category selection
        Spinner spinner = binding.categorySpinner;
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add(getString(R.string.select_none)); // set the first selection as a placeholder for 'no selection'
        for (Category category : categories) {
            spinnerAdapter.add(category.name);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean initialized = false;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!initialized) {
                    initialized = true;
                    return;
                }
                selectedCategory = i;
                if (i == 0) Log.d("__LOG", "Category selection removed");
                else Log.d("__LOG", "Category selected, category:" + categories[i - 1].name);
                refreshProducts(requireContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // configure search
        SearchView searchView = binding.searchProducts;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("__LOG", "Searched for string: " + s);
                refreshProducts(requireContext());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchString = s;
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (searchString.isEmpty()) {
                    Log.d("__LOG", "Search cleared");
                    refreshProducts(requireContext());
                }
                return false;
            }
        });
        return root;
    }

    private void refreshProducts(Context context) {
        ProductService productService = ProductService.getService();
        if (selectedCategory == 0 && searchString.isEmpty()) {
            products = productService.getProducts();
        } else if (searchString.isEmpty()) {
            products = productService.getProductsByCategory(categories[selectedCategory - 1].id); // -1 to remove no-selection category
        } else if (selectedCategory == 0) {
            products = productService.getProductsBySearch(searchString);
        } else {
            products = productService.getProductsBySearch(searchString, categories[selectedCategory - 1].id); // -1 to remove no-selection category
        }

        ProductRecyclerViewAdapter recyclerViewAdapter = new ProductRecyclerViewAdapter(context, products);
        binding.recyclerViewProducts.setAdapter(recyclerViewAdapter);
    }
}