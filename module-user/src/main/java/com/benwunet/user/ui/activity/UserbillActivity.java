package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserBillBinding;
import com.benwunet.user.ui.adapter.UserBillAdapter;
import com.benwunet.user.ui.viewmodel.BillItemViewModel;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserbillActivity
 * @Description: 用户账单界面
 * @Author: feng
 * @CreateDate: 2020/11/21 0021 13:50
 * @Version: 1.0
 */


public class UserbillActivity extends BaseActivity<ActivityUserBillBinding, BaseViewModel> {
    private Context mContext;
    private UserBillAdapter adapter;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_bill;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.tvMonth.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                TimePickerView pvTime = new TimePickerBuilder(UserbillActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
                        String time = simpleDateFormat.format(date);
                        binding.tvMonth.setText(time);
                    }
                }).setType(new boolean[]{true, true, false, false, false, false}).build();
                pvTime.show();
            }
        });

        adapter = new UserBillAdapter(R.layout.item_user_bill_view, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        startActivity(UserBillDetailsActivity.class);
            }
        });
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        final List<BaseCustomViewModel> data = new ArrayList<>();
        BillItemViewModel themesItemViewModel1 = new BillItemViewModel();
        themesItemViewModel1.money="300";
        BillItemViewModel themesItemViewModel = new BillItemViewModel();
        themesItemViewModel1.money="-1000";
        themesItemViewModel1.time="2020-08-28  15:39";
        BillItemViewModel themesItemViewModel2 = new BillItemViewModel();
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel2);
        adapter.setNewData(data);
        binding.recyclerview.setAdapter(adapter);
        adapter.addChildClickViewIds(R.id.iv_delete);
    }
}
