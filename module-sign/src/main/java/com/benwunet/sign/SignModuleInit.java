package com.benwunet.sign;

import android.app.Application;

import com.benwunet.base.base.IModuleInit;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by feng on 2020/10/15.
 */

public class SignModuleInit implements IModuleInit {
    public static Application getmApplication() {
        return mApplication;
    }

    public void setmApplication(Application mApplication) {
        this.mApplication = mApplication;
    }

    private static Application mApplication;

    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("登录模块初始化 -- onInitAhead");
        mApplication=application;

        return false;

    }


    @Override
    public boolean onInitLow(Application application) {
        KLog.e("登录模块初始化 -- onInitLow");
//        DemoHelper.getInstance().init(application);
        return false;
    }


}
