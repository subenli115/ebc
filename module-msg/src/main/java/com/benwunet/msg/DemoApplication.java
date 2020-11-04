package com.benwunet.msg;

import android.app.Application;
import android.content.Context;

import com.benwunet.base.base.IModuleInit;
import com.benwunet.msg.common.interfaceOrImplement.UserActivityLifecycleCallbacks;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by feng on 2020/10/15.
 */

public class DemoApplication implements IModuleInit {
    public static Application getInstance() {
        return mApplication;
    }
    private static UserActivityLifecycleCallbacks mLifecycleCallbacks = new UserActivityLifecycleCallbacks();
    public static UserActivityLifecycleCallbacks getLifecycleCallbacks() {
        return mLifecycleCallbacks;
    }
    private static Application mApplication;

    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("消息模块初始化 -- onInitAhead");
        mApplication=application;
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("消息模块初始化 -- onInitLow");
        return false;
    }







    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

}
