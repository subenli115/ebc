package com.benwunet.cards;

import android.app.Application;

import com.benwunet.base.base.IModuleInit;

import me.goldze.mvvmhabit.utils.KLog;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by feng on 2020/10/15.
 */

public class CardsModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("名片夹模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("名片夹模块初始化 -- onInitLow");
        return false;
    }
}
