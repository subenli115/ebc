package com.benwunet.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.model.BaseSingleLiveEvent;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.home.ui.adapter.CompanyIndustryAdapter;
import com.benwunet.home.ui.bean.IndustryListBean;
import com.benwunet.home.ui.respository.MyCardRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CompanyIndustryViewModel extends BaseViewModel {
    private MyCardRepository repository = MyCardRepository.getInstance(this);
    public MutableLiveData<List<IndustryListBean>> industry = new MutableLiveData<>();
    public BaseSingleLiveEvent finish = new BaseSingleLiveEvent<>();

    public CompanyIndustryViewModel(@NonNull Application application) {
        super(application);
        repository.getIndustry(industry);
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

    public List<IndustryListBean> initListData(String string, List<IndustryListBean> industryListBeans, CompanyIndustryAdapter adapter) {
        if (string.length() > 0) {
            List<String> strings = Arrays.asList(string.split(","));
            adapter.setSelectList(strings);
            for (int i = 0; i < strings.size(); i++) {
                for (int k = 0; k < industryListBeans.size(); k++) {
                    if (strings.get(i).equals(industryListBeans.get(k).getIndustryName())) {
                        industryListBeans.get(k).setSelect(true);
                    }
                }
            }
        }
            return industryListBeans;

    }
}
