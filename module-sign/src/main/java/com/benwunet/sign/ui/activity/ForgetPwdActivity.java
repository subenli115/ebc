package com.benwunet.sign.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.benwunet.base.global.IConstants;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityForgetPwdBinding;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;
import com.benwunet.sign.ui.wdiget.SmsCodeView;

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
        viewModel.isSend.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean code) {
                binding.smsCodeView.startDjs();
            }
        });
        binding.smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type) {
                viewModel.getCode(IConstants.RES);
            }
        });
    }

    @Override
    public void initData() {
        binding.smsCodeView.setTvPhone(binding.etPhone);
    }

}