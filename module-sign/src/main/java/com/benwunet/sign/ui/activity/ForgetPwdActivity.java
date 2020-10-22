package com.benwunet.sign.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.benwunet.base.global.IConstants;
import com.benwunet.base.wdiget.SmsCodeView;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityForgetPwdBinding;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;

import java.util.Map;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: ForGetPwdActivity
 * @Description: 找回密码
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 15:13
 * @Version: 1.0
 */


public class ForgetPwdActivity extends BaseActivity<ActivityForgetPwdBinding, LoginViewModel> {

    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
    }

    @Override
    public void initData() {
        binding.smsCodeView.setTvPhone(binding.etPhone);
        binding.smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                viewModel.getCode(IConstants.REG);
            }
        });
        viewModel.verifyCode.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean code) {
                binding.smsCodeView.startDjs();
            }
        });
    }

}