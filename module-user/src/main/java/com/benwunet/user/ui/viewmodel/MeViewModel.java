package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.benwunet.user.ui.activity.UserHomeActivity;
import com.benwunet.user.ui.activity.UserSettingActivity;
import com.benwunet.user.ui.bean.MeHomeBean;
import com.benwunet.user.ui.respository.MeRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 *
 * 个人中心
 * Created by feng on 2020/10/15.
 */

public class MeViewModel extends BaseViewModel {
    public SingleLiveEvent<MeHomeBean> homeBean = new SingleLiveEvent<>();
    public SingleLiveEvent<String> imgUrl = new SingleLiveEvent<>();

    private MeRepository meRepository = MeRepository.getInstance(this);
    public MeHomeBean homeEntity;

    public MeViewModel(@NonNull Application application) {
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
        meRepository.getMemberHome(homeBean);
    }

}
