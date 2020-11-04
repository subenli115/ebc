package com.benwunet.main.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.benwunet.msg.common.net.Resource;
import com.benwunet.msg.common.repositories.EMClientRepository;

public class SplashViewModel extends AndroidViewModel {
    private final Application mApplication;
    private EMClientRepository mRepository;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        mApplication=application;
        mRepository = new EMClientRepository();
    }

    public LiveData<Resource<Boolean>> getLoginData() {
        return mRepository.loadAllInfoFromHX(mApplication);
    }
}
