package com.benwunet.sign.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Package: com.benwunet.sign.ui
 * @ClassName: InfoViewModel
 * @Description: 完善资料业务
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:22
 * @Version: 1.0
 */


public class InfoViewModel extends BaseViewModel {


    public InfoViewModel(@NonNull Application application) {
        super(application);
    }

    //关闭页面按钮
    public BindingCommand closeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


}
