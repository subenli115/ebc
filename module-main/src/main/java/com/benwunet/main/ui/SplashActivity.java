package com.benwunet.main.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.base.router.RouterActivityPath;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * Created by feng on 2020/10/15.
 * 冷启动
 */

public class SplashActivity extends Activity {

    private boolean isFirstUse;

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
        //实例化Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean(SPKeyGlobal.USE_FIRST, false);
        //提交修改
        editor.commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inMain();
            }
        }, 3 * 1000);
        XXPermissions.with(this)
                .permission(Permission.RECORD_AUDIO)
                .permission(Permission.READ_PHONE_STATE)
                .permission(Permission.CAMERA)
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
    }

    /**
     * 进入主页面
     */
    private void inMain() {
        //采用ARouter+RxBus实现组件间通信
        ARouter.getInstance().build(RouterActivityPath.Sign.PAGER_LOGIN).navigation();
        finish();
    }
}
