package com.benwunet.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.model.BaseViewModel;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.respository.MyCardRepository;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;


/**
 * Created by feng on 2020/10/15.
 */

public class HomeViewModel extends BaseViewModel {
    public MutableLiveData<CardDetailsBean> cardDetailsBeanMutableLiveData = new MutableLiveData<>();
    private MyCardRepository repository = MyCardRepository.getInstance(this);
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repository.getCardDetails(cardDetailsBeanMutableLiveData);

    }



}
