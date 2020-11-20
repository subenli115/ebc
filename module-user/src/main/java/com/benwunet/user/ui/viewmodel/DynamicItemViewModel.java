package com.benwunet.user.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * Created by feng on 2017/7/17.
 */

public class DynamicItemViewModel extends ItemViewModel {
    public ObservableField<String> text = new ObservableField<>();

    public DynamicItemViewModel(@NonNull BaseViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }
}
