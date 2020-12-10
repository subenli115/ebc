package com.benwunet.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.model.BaseSingleLiveEvent;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.home.ui.adapter.CompanyIndustryAdapter;
import com.benwunet.home.ui.adapter.HomeGroupChatAdapter;
import com.benwunet.home.ui.bean.HomeGroupListBean;
import com.benwunet.home.ui.bean.IndustryListBean;
import com.benwunet.home.ui.respository.MyCardRepository;

import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GroupListViewModel extends BaseViewModel {
    private MyCardRepository repository = MyCardRepository.getInstance(this);
    public  MutableLiveData<List<HomeGroupListBean>> homeGroupList= new MutableLiveData<>();
    public BaseSingleLiveEvent finish = new BaseSingleLiveEvent<>();

    public GroupListViewModel(@NonNull Application application) {
        super(application);
        repository.getGroupList(homeGroupList);
    }


    public String getProfession(List<String> selectList) {
        String selectString="";
        if(selectList.size()>0) {
             selectString = String.join(",", selectList);
        }
        return selectString;

    }


    public BindingCommand performOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish.call();
        }
    });

}
