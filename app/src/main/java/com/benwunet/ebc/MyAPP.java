package com.benwunet.ebc;

import com.benwunet.base.config.ModuleLifecycleConfig;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by feng on 2020/10/15.
 */

public class MyAPP extends BaseApplication {
    //用户token
    private static String accessToken;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
