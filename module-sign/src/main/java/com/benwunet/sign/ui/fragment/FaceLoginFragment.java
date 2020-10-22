package com.benwunet.sign.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.FragmentSignFaceBinding;
import com.benwunet.sign.ui.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.sign.ui.fragment
 * @ClassName: FaceLoginActivity
 * @Description: 密码登录
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */


public class FaceLoginFragment extends BaseFragment<FragmentSignFaceBinding, LoginViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sign_face;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    }
}
