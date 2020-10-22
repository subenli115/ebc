package com.benwunet.main.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract._Login;
import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.main.R;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

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
