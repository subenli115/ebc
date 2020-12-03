package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.BottomDialog;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserRealNameBinding;
import com.benwunet.user.ui.viewmodel.SettingViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserRealNameActivity
 * @Description: 用户实名认证
 * @Author: feng
 * @CreateDate: 2020/11/14 0014 09:59
 * @Version: 1.0
 */


public class UserRealNameActivity extends BaseActivity<ActivityUserRealNameBinding, SettingViewModel> {
    private Context mContext;
    private BottomDialog mDialog;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_real_name;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.tvNext.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
                startActivity(UserRealInfoActivity.class);
            }
        });

        binding.llUserCardPositive.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPhotoDialog(mContext, binding.ivPositive);
            }
        });
        binding.llUserCardHold.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPhotoDialog(mContext, binding.ivHold);
            }
        });
        binding.llUserCardBack.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPhotoDialog(mContext, binding.ivBack);
            }
        });
    }

    public void showPhotoDialog(final Context context, final View v) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = new BottomDialog(this,(ImageView) v);
        mDialog.show();
    }
}