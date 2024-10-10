package com.example.webberapp.ui.browse;

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


public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // fetch data
        BrowseFragmentHelper helper = new BrowseFragmentHelper();
        Product[] products = helper.getProducts();
        final Category[] categories = helper.getCategories();

        if (products.length == 0) return root;

        // configure recycler view for products
        RecyclerView recyclerView = binding.recyclerViewProducts;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ProductRecyclerViewAdapter recyclerViewAdapter = new ProductRecyclerViewAdapter(requireContext(), products);
        recyclerView.setAdapter(recyclerViewAdapter);

        // configure categories spinner
        Spinner spinner = binding.categorySpinner;
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add(getString(R.string.select_none)); // set the first selection as a placeholder for 'no selection'
        for (Category category : categories) {
            spinnerAdapter.add(category.name);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Log.d("__LOG", "Category selection removed");
                    return;
                }
                Log.d("__LOG", "Category selected, category:" + categories[i - 1].name);
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return root;
    }
}