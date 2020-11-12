package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserPublicInfoBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserPublicInfoActivity
 * @Description: 公开信息
 * @Author: feng
 * @CreateDate: 2020/11/11 0011 17:21
 * @Version: 1.0
 */


public class UserPublicInfoActivity extends BaseActivity<ActivityUserPublicInfoBinding, BaseViewModel> {
    private Context mContext;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_public_info;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext=this;
    }

    @Override
    public void initViewObservable() {

    }
}
