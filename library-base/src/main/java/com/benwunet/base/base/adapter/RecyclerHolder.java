package com.benwunet.base.base.adapter;


import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final B binding;

    public RecyclerHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}