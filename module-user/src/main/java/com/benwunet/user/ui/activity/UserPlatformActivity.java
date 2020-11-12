package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserPlatformBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserPlatformActivity
 * @Description: 第三方平台
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 15:04
 * @Version: 1.0
 */


public class UserPlatformActivity extends BaseActivity<ActivityUserPlatformBinding, BaseViewModel> {
    private Context mContext;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_platform;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext=this;
        binding.igvAlipay.setLeftImage();
        binding.igvWechat.setLeftImage();
        binding.igvWeibo.setLeftImage();
    }
}