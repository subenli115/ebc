package com.benwunet.sign.ui.source.local;


import com.benwunet.base.global.SPKeyGlobal;
import com.benwunet.sign.ui.source.LocalDataSource;

import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * 本地数据源，可配合Room框架使用
 * Created by goldze on 2019/3/26.
 */
public class LocalDataSourceImpl implements LocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl() {
        //数据库Helper构建
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put(SPKeyGlobal.USERNAME, userName);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.getInstance().put(SPKeyGlobal.PASSWORD, password);
    }

    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString(SPKeyGlobal.USERNAME);
    }

    @Override
    public String getPassword() {
        return SPUtils.getInstance().getString(SPKeyGlobal.PASSWORD);
    }






}
