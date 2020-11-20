package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.benwunet.user.BR;
import com.benwunet.user.R;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by feng on 2018/7/18.
 */

public class ViewPagerViewModel extends BaseViewModel {
    public SingleLiveEvent<String> itemClickEvent = new SingleLiveEvent<>();
    public ViewPagerViewModel(@NonNull Application application) {
        super(application);

        for (int i = 1; i <= 2; i++) {
            UserViewPagerItemViewModel itemViewModel = new UserViewPagerItemViewModel(this, "第" + i + "个页面");
            items.add(itemViewModel);
        }

    }

    public void initData(String[] stringArray){
        for(int i=0;i<stringArray.length;i++){
            pageNames.add(stringArray[i]);
        }
    }
    public ObservableList<String> pageNames = new ObservableArrayList<>();
    //给ViewPager添加ObservableList
    public ObservableList<UserViewPagerItemViewModel> items = new ObservableArrayList<>();
    //给ViewPager添加ItemBinding
    public ItemBinding<UserViewPagerItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.user_item_viewpager);
    //给ViewPager添加PageTitle
    public final BindingViewPagerAdapter.PageTitles<UserViewPagerItemViewModel> pageTitles = new BindingViewPagerAdapter.PageTitles<UserViewPagerItemViewModel>() {
        @Override
        public CharSequence getPageTitle(int position, UserViewPagerItemViewModel item) {

            return pageNames.get(position);
        }
    };
    //ViewPager切换监听
    public BindingCommand<Integer> onPageSelectedCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer index) {
            ToastUtils.showShort("ViewPager切换：" + index);
        }
    });
}
