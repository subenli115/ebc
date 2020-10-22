package com.benwunet.sign.ui.activity;

import android.os.Bundle;

import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityRegisterBinding;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;

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

    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
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
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
    }

    @Override
    public void initData() {
    }

}
