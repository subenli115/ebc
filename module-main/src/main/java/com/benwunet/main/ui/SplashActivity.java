package com.benwunet.main.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.msg.common.interfaceOrImplement.OnResourceParseCallback;
import com.benwunet.msg.section.base.BaseActivity;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * Created by feng on 2020/10/15.
 * 冷启动
 */

public class SplashActivity extends BaseActivity {

    private boolean isFirstUse;
    private SplashViewModel model;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(SPKeyGlobal.USE_FIRST,MODE_PRIVATE);
        isFirstUse = preferences.getBoolean(SPKeyGlobal.USE_FIRST, true);
        /**
         *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
         */
//        if (false) {
//            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
//        }
//        finish();
        //实例化Editor对象、
        model= ViewModelProviders.of(this).get(SplashViewModel.class);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean(SPKeyGlobal.USE_FIRST, false);
        //提交修改
        editor.commit();
//        inMain();
        XXPermissions.with(this)
                .permission(Permission.RECORD_AUDIO)
                .permission(Permission.READ_PHONE_STATE)
                .permission(Permission.CAMERA)
                .permission(Permission.ACCESS_FINE_LOCATION)
                .permission(Permission.ACCESS_COARSE_LOCATION)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                // 申请多个权限
                .permission(Permission.Group.STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean never) {
                    }
                });
        loginSDK();
//        inMain();
    }


    /**
     * 进入主页面
     */
    private void inMain() {
        //采用ARouter+RxBus实现组件间通信
        MainActivity.startAction(mContext);
//        finish();
//        ARouter.getInstance().build(RouterActivityPath.Sign.PAGER_LOGIN).navigation();
        finish();
    }

    /**
     * 进入登录页面
     */
    private void inLogin() {
        //采用ARouter+RxBus实现组件间通信
        ARouter.getInstance().build(RouterActivityPath.Sign.PAGER_LOGIN).navigation();
        finish();
    }

    private void loginSDK() {
        model.getLoginData().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>(true) {
                @Override
                public void onSuccess(Boolean data) {
                    inMain();
                }

                @Override
                public void onError(int code, String message) {
                    super.onError(code, message);
                    inLogin();
                }
            });

        });
    }
}
