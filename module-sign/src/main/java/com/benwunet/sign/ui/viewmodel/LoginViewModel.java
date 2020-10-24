package com.benwunet.sign.ui.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.benwunet.base.global.IConstants;
import com.benwunet.sign.ui.activity.FaceDetectExpActivity;
import com.benwunet.sign.ui.activity.ForgetPwdActivity;
import com.benwunet.sign.ui.activity.InputInfoFirstActivity;
import com.benwunet.sign.ui.respository.SignRepository;
import com.benwunet.sign.ui.source.local.LocalDataSourceImpl;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.StringUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.sign.ui
 * @ClassName: InfoViewModel
 * @Description: 登录注册业务
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:22
 * @Version: 1.0
 */

public class LoginViewModel extends BaseViewModel {

    private SignRepository signRepository = SignRepository.getInstance(LocalDataSourceImpl.getInstance(), this);
    //密码的绑定
    public SingleLiveEvent<String> password = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isRemember = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> privacyCheck = new SingleLiveEvent<>();
    public SingleLiveEvent<String> verifyCode = new SingleLiveEvent<>();
    public SingleLiveEvent<String> confirm = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isSend = new SingleLiveEvent<>();
    public SingleLiveEvent<String> phone = new SingleLiveEvent<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        phone.setValue(signRepository.getUserName());
    }


    //获取验证码
    public SingleLiveEvent<Boolean> getCode(String type) {
        if (phone != null && phone.getValue().length() == 0) {
            ToastUtils.showLong("请输入手机号");
        } else {
            isSend = signRepository.getVerifyCode(phone.getValue(), isSend, type);
        }
        return isSend;
    }

    //注册页面的点击事件
    public BindingCommand register = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            register(IConstants.REG);
        }
    });
    //人脸页面的点击事件
    public BindingCommand faceOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FaceDetectExpActivity.class);
        }
    });


    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });
    //注册页面的点击事件
    public BindingCommand registerOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(InputInfoFirstActivity.class);
        }
    });

    //找回密码的点击事件
    public BindingCommand getPwdOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ForgetPwdActivity.class);
        }
    });

    //关闭页面按钮
    public BindingCommand closeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //短信登录按钮的点击事件
    public BindingCommand codeLoginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            codelogin();
        }
    });

    //找回密码的点击事件
    public BindingCommand forgetOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            register(IConstants.RES);
        }
    });



    /**
     * 登录操作
     **/
    private void login() {
        if (TextUtils.isEmpty(phone.getValue())) {
            ToastUtils.showShort("请输入手机号！");
            return;
        }
        if (TextUtils.isEmpty(password.getValue())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        signRepository.login(phone.getValue(), password.getValue());

    }

    /**
     * 验证码登录操作
     **/
    private void codelogin() {
        if (TextUtils.isEmpty(phone.getValue())) {
            ToastUtils.showShort("请输入手机号！");
            return;
        }
        if (TextUtils.isEmpty(verifyCode.getValue())) {
            ToastUtils.showShort("请输入验证码！");
            return;
        }
        signRepository.codeLogin(phone.getValue(), verifyCode.getValue());
    }

    /**
     * 注册或找回操作
     *
     * @param type 类型
     *
     * */
    private void register(String type) {
        String phoneValue = phone.getValue();
        if (StringUtils.isEmpty(phoneValue) || phoneValue.length() != 11) {
            ToastUtils.showLong("手机不正确");
            return;
        }
        if (TextUtils.isEmpty(verifyCode.getValue())) {
            ToastUtils.showShort("请输入验证码！");
            return;
        }
        if (StringUtils.isEmpty(password.getValue())) {
            ToastUtils.showLong("密码不能为空");
            return;
        }
        if (!StringUtils.equals(password.getValue(), confirm.getValue())) {
            ToastUtils.showLong("密码不一致");
            return;
        }
        signRepository.register(phoneValue, password.getValue(), confirm.getValue(), verifyCode.getValue(),type);
    }








}
