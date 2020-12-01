package com.benwunet.user.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.user.ui.activity.UserHomeActivity;
import com.benwunet.user.ui.activity.UserSettingActivity;
import com.benwunet.user.ui.bean.MeHomeBean;
import com.benwunet.user.ui.bean.MeSafeBean;
import com.benwunet.user.ui.respository.MeRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 *
 * 个人中心
 * Created by feng on 2020/10/15.
 */

public class MeViewModel extends BaseViewModel {
    public MutableLiveData<MeHomeBean> homeBean = new MutableLiveData<>();
    public MutableLiveData<MeSafeBean> safeBean = new MutableLiveData<>();
    public MutableLiveData<String> mobile = new MutableLiveData<>();
    public SingleLiveEvent<Boolean> isSend = new SingleLiveEvent<>();
    //用户名的绑定
    private MeRepository meRepository = MeRepository.getInstance(this);
    public MeHomeBean homeEntity;

    public MeViewModel(@NonNull Application application) {
        super(application);
    }
    @Override
    public void onCreate() {
        initData();
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


    //获取验证码
    public SingleLiveEvent<Boolean> getCode(String phone, String type) {
        if (phone != null && phone.length() == 0) {
            ToastUtils.showLong("请输入手机号");
        } else {
             meRepository.getVerifyCode(phone, isSend, type);
        }
        return isSend;
    }

    public void initData() {
        meRepository.getMemberHome(homeBean);
        meRepository.getMemberSafeInfo(safeBean);
    }

}
