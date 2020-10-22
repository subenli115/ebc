package com.benwunet.ebc;

import com.benwunet.base.config.ModuleLifecycleConfig;
import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.base.utils.SPUtils;

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
    public static void setAccessToken(String accessToken) {
        MyAPP.accessToken = accessToken;
    }


    public static String getAccessToken() {
        if(accessToken==null||accessToken.length()==0){
            accessToken = SPUtils.getString(SPKeyGlobal.TOKEN);
        }
        return accessToken;
    }
}
