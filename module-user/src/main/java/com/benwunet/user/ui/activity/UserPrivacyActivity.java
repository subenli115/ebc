package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserPrivacyBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserPrivacyActivity
 * @Description: 隐私页面
 * @Author: feng
 * @CreateDate: 2020/11/11 0011 14:22
 * @Version: 1.0
 */


public class UserPrivacyActivity extends BaseActivity<ActivityUserPrivacyBinding, BaseViewModel> {
    private Context mContext;
    private PromptDialog promptDialog;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_privacy;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        promptDialog = new PromptDialog(this);
        binding.igvSend.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                PromptButton cancle = new PromptButton("取消", null);
                cancle.setTextColor(Color.parseColor("#0076ff"));
                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("仅好友", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                binding.igvSend.setRigthText("仅好友");
                            }
                        }), new PromptButton("所有人", new PromptButtonListener() {
                            @Override
                            public void onClick(PromptButton button) {
                                binding.igvSend.setRigthText("所有人");
                            }
                        })
                );
            }
        });
        binding.igvOpenInfo.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(new Intent(mContext,UserPublicInfoActivity.class));
            }
        });
    }

    @Override
    public void initViewObservable() {


    }
}