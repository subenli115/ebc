package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserSafeBinding;
import com.benwunet.user.ui.bean.MeSafeBean;
import com.benwunet.user.ui.viewmodel.MeViewModel;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserSafeActivity
 * @Description: 账号安全
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 10:23
 * @Version: 1.0
 */


public class UserSafeActivity extends BaseActivity<ActivityUserSafeBinding, MeViewModel> {
    private Context mContext;
    private String mobile;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_safe;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.igvPhone.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserPhoneActivity.startAction(mobile);
            }
        });

        binding.igvPwd.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserPwdActivity.startAction(mobile);
            }
        });
        binding.igvThird.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserPlatformActivity.class);
            }
        });
    }

    @Override
    public void initViewObservable() {
        viewModel.safeBean.observe(this, new Observer<MeSafeBean>() {
            @Override
            public void onChanged(MeSafeBean bean) {
                mobile = bean.getMobile();
                binding.igvPhone.setMobileRigthText(bean.getMobile());
                binding.igvMail.setRigthText(bean.getEmail());
                binding.igvFace.setRigthText(bean.isIsAuth() ? "已设置" : "未设置");
                viewModel.mobile.setValue(mobile);
            }
        });
    }
}