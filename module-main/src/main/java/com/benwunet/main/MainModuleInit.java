package com.benwunet.main;

import android.app.Application;

import com.benwunet.base.base.IModuleInit;
import com.benwunet.base.base.loadsir.EmptyCallback;
import com.benwunet.base.base.loadsir.ErrorCallback;
import com.benwunet.base.base.loadsir.LoadingCallback;
import com.benwunet.base.base.loadsir.TimeoutCallback;
import com.benwunet.base.global.SPKeyGlobal;
import com.kingja.loadsir.core.LoadSir;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.GsonDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;

import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.StringUtils;

/**
 * Created by feng on 2020/10/15.
 */

public class MainModuleInit implements IModuleInit {
    private static String accessToken;
    private HttpHeaders headers;


    public static String getAccessToken() {
        if (accessToken == null || accessToken.length() == 0) {
            accessToken= SPUtils.getInstance().getString(SPKeyGlobal.TOKEN);
        }
        return accessToken;
    }

    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("主业务模块初始化 -- onInitAhead");
        /**
         * 预先加载一级列表显示 全国所有城市市的数据
         */
        CityListLoader.getInstance().loadCityData(application.getApplicationContext());
        EasyHttp.init(application);
        if (BuildConfig.DEBUG) {
            EasyHttp.getInstance().debug("easyhttp", true);
        }
        if (!StringUtils.isEmpty(getAccessToken())) {
            headers = new HttpHeaders();
            headers.put("Authorization", "Bearer " + getAccessToken());
        }
        EasyHttp.getInstance()
                .setBaseUrl("http://10.10.0.111:8080/")
                .setReadTimeOut(15 * 1000)
                .setWriteTimeOut(15 * 1000)
                .setConnectTimeout(15 * 1000)
                .setRetryCount(3)
                .addCommonHeaders(headers)
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
