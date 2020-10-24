package com.benwunet.sign.ui.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * @Package: com.benwunet.sign.ui
 * @ClassName: InfoViewModel
 * @Description: 完善资料业务
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:22
 * @Version: 1.0
 */


public class InfoViewModel extends BaseViewModel {

    public SingleLiveEvent<String> name = new SingleLiveEvent<>();
    public SingleLiveEvent<String> company = new SingleLiveEvent<>();
    public SingleLiveEvent<String> job = new SingleLiveEvent<>();
    public SingleLiveEvent<String> industry = new SingleLiveEvent<>();

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

    //下一步按钮
    public BindingCommand nextOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {


        }
    });

    //时间选择按钮
    public BindingCommand timeOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            TimePickerView pvTime = new TimePickerBuilder(getApplication(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {

                }
            }).build();
        }

    });

    //跳过按钮
    public BindingCommand skipOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
        }
    });




}
