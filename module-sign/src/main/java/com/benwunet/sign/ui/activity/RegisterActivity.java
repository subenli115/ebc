package com.benwunet.sign.ui.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.benwunet.base.utils.RSAUtil;
import com.benwunet.base.utils.RSAUtils;
import com.benwunet.base.wdiget.SmsCodeView;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityLoginBinding;
import com.benwunet.sign.databinding.ActivityRegisterBinding;
import com.benwunet.sign.ui.LoginActivity;
import com.benwunet.sign.ui.LoginViewModel;
import com.benwunet.sign.ui.adapter.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: RegisterActivity
 * @Description: 注册页面
 * @Author: feng
 * @CreateDate: 2020/10/21 0021 16:20
 * @Version: 1.0
 */


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, LoginViewModel> {

    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
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
                viewModel.getCode("REG");
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