package com.benwunet.user.ui.viewmodel;


import androidx.annotation.NonNull;

import com.benwunet.base.contract.BaseCustomViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-23
 */
public class ThemesItemViewModel extends BaseCustomViewModel {
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
