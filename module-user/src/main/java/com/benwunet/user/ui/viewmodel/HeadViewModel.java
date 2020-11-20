package com.benwunet.user.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.user.ui.viewmodel
 * @ClassName: HeadViewModel
 * @Description: java类作用描述
 * @Author: feng
 * @CreateDate: 2020/11/16 0016 18:36
 * @Version: 1.0
 */


public class HeadViewModel extends BaseViewModel {

    public HeadViewModel(@NonNull Application application) {
        super(application);
        
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}