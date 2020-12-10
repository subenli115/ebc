package com.benwunet.base.base.viewmodel;

import androidx.annotation.NonNull;

import com.benwunet.base.model.BaseViewModel;

public class ItemViewModel<VM extends BaseViewModel> {
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
