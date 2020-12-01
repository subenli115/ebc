package com.benwunet.find.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.benwunet.base.base.viewmodel.BaseHttpViewModel;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.ui.activity.FindCityDetailsActivity;
import com.benwunet.find.ui.bean.MeCardCollectionBean;
import com.benwunet.find.ui.listener.OnDemoListener;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * Created by feng on 2020/10/15.
 */

public class FindViewModel extends BaseHttpViewModel {


    public FindViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<MeCardCollectionBean> data = new SingleLiveEvent<>();

    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 6; i++) {
            observableList.add(new DynamicItemViewModel());
        }
        itemBinding.bindExtra(BR.onDemoListener,onDemoListener);
    }


    public OnDemoListener onDemoListener = new OnDemoListener() {
        @Override
        public void onDemoItemClick() {
            startActivity(FindCityDetailsActivity.class);
        }
    };


    //给RecyclerView添加ItemBinding
    public ItemBinding<DynamicItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_find_city);
    //给RecyclerView添加ObservableList
    public ObservableList<DynamicItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<DynamicItemViewModel> itemBinding2 = ItemBinding.of(BR.viewModel, R.layout.item_find_city_contacts);
    //关闭页面按钮

}
