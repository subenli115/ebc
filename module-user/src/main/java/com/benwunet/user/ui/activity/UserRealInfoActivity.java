package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserRealInfoBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserRealInfoActivity
 * @Description: 用户实名信息界面
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 17:31
 * @Version: 1.0
 */


public class UserRealInfoActivity extends BaseActivity<ActivityUserRealInfoBinding, BaseViewModel> implements CustomAdapt {
    private Context mContext;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_real_info;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.tvConfirm.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
                startActivity(new Intent(mContext,UserRealResultActivity.class));
            }
        });
    }

    @Override
    public void initViewObservable() {
        binding.checkboxMan.setChecked(true);
        binding.checkboxWoman.setChecked(false);
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 858;
    }
}