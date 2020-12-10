package com.benwunet.cards.ui.viewmodel;

import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.benwunet.base.base.viewmodel.ItemViewModel;
import com.benwunet.base.model.BaseViewModel;

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
