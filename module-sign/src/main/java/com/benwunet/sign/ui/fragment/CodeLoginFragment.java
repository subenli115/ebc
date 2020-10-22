package com.benwunet.sign.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.benwunet.base.global.IConstants;
import com.benwunet.sign.ui.wdiget.SmsCodeView;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.FragmentSignCodeBinding;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;

import java.util.Map;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * @Package: com.benwunet.sign.ui.fragment
 * @ClassName: CodeLoginFragment
 * @Description: 短信登录
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 16:06
 * @Version: 1.0
 */


public class CodeLoginFragment extends BaseFragment<FragmentSignCodeBinding, LoginViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sign_code;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.smsCodeView.setTvPhone(binding.etPhone);
        binding.smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                viewModel.getCode(IConstants.LOGIN);
            }
        });
        viewModel.isSend.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean code) {
                    binding.smsCodeView.startDjs();
            }
        });
    }

}