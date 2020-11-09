package com.benwunet.user.ui.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import com.benwunet.base.global.SPKeyGlobal;

import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * Created by feng on 2020/10/15.
 */

public class MeViewModel extends BaseViewModel {
    public ObservableInt loginBtnVisible = new ObservableInt();
    public SingleLiveEvent<String> userInfoEvent = new SingleLiveEvent<>();
    private Disposable subscribe;
    public MeViewModel(@NonNull Application application) {
        super(application);
    }
    @Override
    public void onCreate() {
        initData();
    }

    public void initData() {
        String userInfo = SPUtils.getInstance().getString(SPKeyGlobal.USER_INFO);
        if (!TextUtils.isEmpty(userInfo)) {
            userInfoEvent.setValue(userInfo);
            loginBtnVisible.set(View.GONE);
        } else {
            loginBtnVisible.set(View.VISIBLE);
        }
    }

}
