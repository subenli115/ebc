package com.benwunet.sign;

import android.app.Application;

import com.benwunet.base.base.IModuleInit;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by feng on 2020/10/15.
 */

public class SignModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("登录模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("登录模块初始化 -- onInitLow");
        return false;
    }
}
