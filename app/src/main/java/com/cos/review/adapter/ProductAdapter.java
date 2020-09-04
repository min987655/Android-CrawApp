package com.cos.review.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.review.R;
import com.cos.review.databinding.ContainerItemBinding;
import com.cos.review.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContainerItemBinding containerItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.container_item,
                parent,
                false
        );
        return new ProductHolder(containerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.containerItemBinding.setProduct(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{

        private ContainerItemBinding containerItemBinding;

        public ProductHolder(@NonNull ContainerItemBinding containerItemBinding) {
            super(containerItemBinding.getRoot());
            this.containerItemBinding = containerItemBinding;
        }
    }
}
