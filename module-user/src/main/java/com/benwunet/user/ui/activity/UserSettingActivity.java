package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserSettingBinding;
import com.benwunet.user.ui.viewmodel.SettingViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserSettingActivity
 * @Description: 设置界面
 * @Author: feng
 * @CreateDate: 2020/11/10 0010 15:51
 * @Version: 1.0
 */

@Route(path = RouterActivityPath.Sign.PAGER_LOGIN)
public class UserSettingActivity extends BaseActivity<ActivityUserSettingBinding, SettingViewModel> {
    private Context mContext;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_setting;
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
        binding.igvInfo.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserInfoActivity.class);
            }
        });
        binding.igvNotice.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserNoticeActivity.class);
            }
        });
        binding.igvPrivacy.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserPrivacyActivity.class);
            }
        });
        binding.igvCache.setOnClickListener(new OnNoDoubleClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {
                MaterialDialogUtils.showBasicDialog(mContext,getString(R.string.user_dialog_warning),
                        getString(R.string.user_dialog_content_clear_cache)).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                });
            }
        });
        binding.igvSafe.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserSafeActivity.class);
            }
        });
        binding.igvProve.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
//                startActivity(UserRealProveActivity.class);
                startActivity(UserBusinessActivity.class);
            }
        });


    }
}
