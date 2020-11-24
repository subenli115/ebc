package com.benwunet.find.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.benwunet.base.contract.BaseCustomViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * Created by feng on 2017/7/17.
 */

public class DynamicItemViewModel extends BaseCustomViewModel {

    public String coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
    public String title = "杨幂";
    public String time = "昨天";
    public String job = "经理";
    public String state = "1";
    public String stateText = "已拒绝";
    public String phone = "18887233425";
    public boolean isSelect;
    public boolean isVisible;


}
