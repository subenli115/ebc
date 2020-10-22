package com.benwunet.cards.ui.viewmodel;

import android.app.Application;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.annotation.NonNull;

import com.benwunet.cards.R;
import com.benwunet.cards.BR;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by feng on 2020/10/15.
 */

public class CardsViewModel extends BaseViewModel {
    public CardsViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 10; i++) {
            observableList.add(new CardsItemViewModel(this, "条目" + i));
        }
    }
    //给RecyclerView添加ObservableList
    public ObservableList<CardsItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CardsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.grid_work);
}
