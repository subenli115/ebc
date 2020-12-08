package com.benwunet.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.model.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CompanyIndustryViewModel extends BaseViewModel {

    public List<BaseCustomViewModel> list;

    public MutableLiveData<Boolean> finish = new MutableLiveData<>();

    public MutableLiveData<List<BaseCustomViewModel>> listData = new MutableLiveData<>();

    public CompanyIndustryViewModel(@NonNull Application application) {
        super(application);
        test();
    }

    private void test() {
        list = new ArrayList<>();
        CompanyIndustryItemViewModel themesItemViewModel = new CompanyIndustryItemViewModel();
        themesItemViewModel.name = "互联网";
        CompanyIndustryItemViewModel themesItemViewModel1 = new CompanyIndustryItemViewModel();
        themesItemViewModel1.name = "餐饮";
        CompanyIndustryItemViewModel themesItemViewModel2 = new CompanyIndustryItemViewModel();
        themesItemViewModel2.name = "电讯";
        CompanyIndustryItemViewModel themesItemViewModel3 = new CompanyIndustryItemViewModel();
        CompanyIndustryItemViewModel themesItemViewModel4 = new CompanyIndustryItemViewModel();
        list.add(themesItemViewModel);
        list.add(themesItemViewModel1);
        list.add(themesItemViewModel2);
        list.add(themesItemViewModel3);
        list.add(themesItemViewModel4);
        listData.setValue(list);
    }

    public CompanyIndustryItemViewModel getProfession() {
        for (int i = 0; i < listData.getValue().size(); i++) {
            CompanyIndustryItemViewModel model = (CompanyIndustryItemViewModel) listData.getValue().get(i);
            if (model.checked) {
                return model;
            }
        }
        return null;

    }

    public void singleCheckPosition(List<BaseCustomViewModel> data, int pos) {
        for (int i = 0; i < data.size(); i++) {
            CompanyIndustryItemViewModel model = (CompanyIndustryItemViewModel) data.get(i);
            model.checked = (pos == i);
        }
    }

    public BindingCommand performOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish.setValue(true);
        }
    });

}
