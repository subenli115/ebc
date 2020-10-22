package com.benwunet.cards.ui.viewmodel;

import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * Created by feng on 2017/7/17.
 */

public class CardsItemViewModel extends ItemViewModel {
    public ObservableField<String> text = new ObservableField<>();

    public CardsItemViewModel(@NonNull BaseViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }
}
