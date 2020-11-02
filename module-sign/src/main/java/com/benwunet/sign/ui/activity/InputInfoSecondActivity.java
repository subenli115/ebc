package com.benwunet.sign.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityInputInfoSecondBinding;
import com.benwunet.sign.ui.bean.CompleteInfoBean;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: InputInfoSecondActivity
 * @Description: 完善资料页面2
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:21
 * @Version: 1.0
 */


public class InputInfoSecondActivity extends BaseActivity<ActivityInputInfoSecondBinding, InfoViewModel> {


    private CompleteInfoBean entity;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_input_info_second;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        binding.setLifecycleOwner(this);
        viewModel.setInfoEntity(entity);
        viewModel.isShowDialog.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                TimePickerView pvTime = new TimePickerBuilder(InputInfoSecondActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        String time = simpleDateFormat.format(date);
                        viewModel.time.setValue(time);
                    }
                }).build();
                pvTime.show();
            }

        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void initParam() {
        //获取列表传入的实体
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            entity = mBundle.getParcelable("entity");
        }
    }
}
