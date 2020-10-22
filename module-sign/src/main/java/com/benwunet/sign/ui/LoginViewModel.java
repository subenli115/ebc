package com.benwunet.sign.ui;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.benwunet.base.global.ApiKey;
import com.benwunet.base.utils.ToastUtil;
import com.benwunet.sign.ui.activity.RegisterActivity;
import com.benwunet.sign.ui.bean.VerifyCodeBean;
import com.benwunet.sign.ui.respository.SignRepository;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by feng on 2020/10/15.
 */

public class LoginViewModel extends BaseViewModel {
    private SignRepository signRepository = SignRepository.getInstance();
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    public SingleLiveEvent<Boolean> verifyCode = new SingleLiveEvent<>();
    public SingleLiveEvent<String> phone = new SingleLiveEvent<>();

    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });

    //获取验证码
    public SingleLiveEvent<Boolean> getCode(String type) {
        if (phone != null && phone.getValue().length() == 0) {
            ToastUtils.showLong("请输入手机号");
        } else {
            verifyCode = signRepository.getVerifyCode(phone.getValue(), verifyCode, type);
        }
        return verifyCode;
    }

    public BindingCommand closeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });
    //注册的点击事件
    public BindingCommand registerOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RegisterActivity.class);
        }
    });

    /**
     * 登录操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }

    }


}
