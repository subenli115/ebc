package com.benwunet.main;

import android.app.Application;
import android.os.Build;

import com.benwunet.base.base.IModuleInit;
import com.benwunet.base.base.loadsir.EmptyCallback;
import com.benwunet.base.base.loadsir.ErrorCallback;
import com.benwunet.base.base.loadsir.LoadingCallback;
import com.benwunet.base.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.GsonDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by feng on 2020/10/15.
 */

public class MainModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("主业务模块初始化 -- onInitAhead");
        EasyHttp.init(application);
        if (BuildConfig.DEBUG)
        {
            EasyHttp.getInstance().debug("easyhttp", true);
        }
        EasyHttp.getInstance()
                .setBaseUrl("http://10.10.0.111:8080/")
                .setReadTimeOut(15 * 1000)
                .setWriteTimeOut(15 * 1000)
                .setConnectTimeout(15 * 1000)
                .setRetryCount(3)
                .setCacheDiskConverter(new GsonDiskConverter())
                .setCacheMode(CacheMode.FIRSTREMOTE);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())

                .addCallback(new TimeoutCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("主业务模块初始化 -- onInitLow");
        return false;
    }
}
