package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.ui.activity.UserHomeActivity;
import com.benwunet.user.ui.activity.UserSettingActivity;
import com.benwunet.user.ui.bean.MeHomeBean;
import com.benwunet.user.ui.respository.MeRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;

/**
 * 个人动态逻辑
 * Created by feng on 2020/10/15.
 */

public class HomeViewModel extends BaseViewModel {


    public SingleLiveEvent<MeHomeBean> homeBean = new SingleLiveEvent<>();
    public SingleLiveEvent<String> imgUrl = new SingleLiveEvent<>();
    //给RecyclerView添加ObservableList
//    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ObservableList
    public ObservableList<DynamicItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<DynamicItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_user_dynamic_img);
    private MeRepository meRepository = MeRepository.getInstance(this);
    public MeHomeBean homeEntity;
    public MergeObservableList<Object> headFooterItems = new MergeObservableList<>()
            .insertItem("Header")
            .insertList(observableList);
    public final OnItemBind<Object> onItemBind = new OnItemBind<Object>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, Object item) {
            itemBinding.set(BR.viewModel, position == 0 ? R.layout.item_user_home_head : R.layout.item_user_dynamic_img);
        }
    };

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        initData();
    }

    public void setHomeEntity(MeHomeBean entity) {
        if (this.homeEntity == null) {
            this.homeEntity = entity;
        }
        imgUrl.setValue(entity.getAvatar());
    }

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //下拉刷新完成
        public SingleLiveEvent<String> finishRefreshing = new SingleLiveEvent<>();
        //上拉加载完成
        public SingleLiveEvent finishLoadmore = new SingleLiveEvent<>();
    }


    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.finishRefreshing.call();

        }
    });
    //上拉加载
    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.finishLoadmore.call();

        }
    });


    //设置页面
    public BindingCommand settingOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(UserSettingActivity.class);
        }
    });

    //动态页面
    public BindingCommand dynamicOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(UserHomeActivity.class);
        }
    });

    public void initData() {
        for (int i = 0; i < 10; i++) {
            observableList.add(new DynamicItemViewModel(this, "条目" + i));
        }
    }

}
