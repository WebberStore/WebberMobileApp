package com.example.webberapp.ui.browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webberapp.R;
import com.example.webberapp.pojo.Product;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private Product[] products;
    private LayoutInflater layoutInflater;

    public ProductRecyclerViewAdapter(Context context, Product[] products) {
        this.products = products;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products[position];
        holder.textView.setText(product.name);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_product_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
