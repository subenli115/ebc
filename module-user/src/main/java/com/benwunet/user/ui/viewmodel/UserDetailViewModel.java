package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 *
 *
 * Created by feng on 2020/10/15.
 */

public class UserDetailViewModel extends BaseViewModel {
    public ObservableField<String> nameObservable = new ObservableField();

    public UserDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setName(String name) {
        nameObservable.set(name);
    }

}
