package com.benwunet.user.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.utils.CommonUtils;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserEditPhoneBinding;
import com.benwunet.user.ui.viewmodel.MeViewModel;
import com.benwunet.user.ui.wdiget.SmsCodeView;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserBindPhoneActivity
 * @Description: 修改手机
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 11:13
 * @Version: 1.0
 */


public class UserPhoneActivity extends BaseActivity<ActivityUserEditPhoneBinding, MeViewModel> {
    private Context mContext;
    private String mobile;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    public static void startAction( String mobile) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, UserPhoneActivity.class);
        intent.putExtra("mobile", mobile);
        activity.startActivity(intent);
    }


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_edit_phone;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext=this;
        if(getIntent()!=null){
            mobile = getIntent().getStringExtra("mobile");
            binding.tvPhone.setText(CommonUtils.setMobileText(mobile));
        }
    }

    @Override
    public void initViewObservable() {
        binding.smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type) {
                viewModel.getCode(IConstants.RES,mobile);
            }
        });

        viewModel.isSend.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.smsCodeView.startDjs();
            }
        });

    }
}
