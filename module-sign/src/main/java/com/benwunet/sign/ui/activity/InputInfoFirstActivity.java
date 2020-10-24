package com.benwunet.sign.ui.activity;

import android.os.Bundle;

import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityRegisterBinding;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: InputInfoFirstActivity
 * @Description: 完善资料页面1
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:21
 * @Version: 1.0
 */


public class InputInfoFirstActivity extends BaseActivity<ActivityRegisterBinding, InfoViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_input_info_first;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
    }

    @Override
    public void initData() {
    }

}
