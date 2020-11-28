package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.user.ui.bean.MeInfoBean;
import com.benwunet.user.ui.respository.MeRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * 设置vm
 * Created by feng on 2020/10/15.
 */

public class SettingViewModel extends BaseViewModel {

    private String cityId;
    private MeRepository meRepository = MeRepository.getInstance(this);
    public SingleLiveEvent<String> time = new SingleLiveEvent<>();
    public SingleLiveEvent<String> city = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> complete = new SingleLiveEvent<>();
    public SingleLiveEvent<MeInfoBean> info = new SingleLiveEvent<>();
    public SingleLiveEvent<String> sign = new SingleLiveEvent<>();
    public SingleLiveEvent<String> sex = new SingleLiveEvent<>();


    public SettingViewModel(@NonNull Application application) {
        super(application);
    }


    //退出登录按钮
    public BindingCommand logoutOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Sign.PAGER_LOGIN).navigation();
        }
    });


    //保存按钮
    public BindingCommand saveOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            MeInfoBean meInfoBean = new MeInfoBean();
//            meInfoBean.setAreaCityId(cityId);
            meInfoBean.setAreaCityName(city.getValue());
            meInfoBean.setBirthday(time.getValue());
            meInfoBean.setGender(Integer.parseInt(sex.getValue()));
            meInfoBean.setMemberSign(sign.getValue());
            meRepository.editMemberInfo(complete,meInfoBean);
        }
    });

    public void setCityId(String id) {
        cityId = id;
    }

    public void initData() {
        meRepository.getMemberInfo(info);
    }

}
