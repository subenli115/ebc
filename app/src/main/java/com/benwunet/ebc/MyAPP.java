package com.benwunet.ebc;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.benwunet.base.config.ModuleLifecycleConfig;
import com.benwunet.msg.common.utils.PreferenceManager;
import com.hyphenate.util.EMLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by feng on 2020/10/15.
 */

public class MyAPP extends BaseApplication implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setCustomFragment(true);
        PreferenceManager.init(this);
        closeAndroidPDialog();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        EMLog.e("demoApp", e.getMessage());
    }


    /**
     * 为了兼容5.0以下使用vector图标
     */
    static {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }


    /**
     * 解决androidP 第一次打开程序出现莫名弹窗
     * 弹窗内容“detected problems with api ”
     */
    private void closeAndroidPDialog(){
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            try {
                Class aClass = Class.forName("android.content.pm.PackageParser$Package");
                Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
                declaredConstructor.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Class cls = Class.forName("android.app.ActivityThread");
                Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
                declaredMethod.setAccessible(true);
                Object activityThread = declaredMethod.invoke(null);
                Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
                mHiddenApiWarningShown.setAccessible(true);
                mHiddenApiWarningShown.setBoolean(activityThread, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
