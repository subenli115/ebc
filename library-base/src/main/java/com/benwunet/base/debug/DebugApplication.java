package com.benwunet.base.debug;

import com.benwunet.base.config.ModuleLifecycleConfig;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by feng on 2020/10/15.
 * debug包下的代码不参与编译，仅作为独立模块运行时初始化数据
 */

public class DebugApplication extends BaseApplication {
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
