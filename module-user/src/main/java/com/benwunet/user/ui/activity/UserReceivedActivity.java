package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserCommonBinding;
import com.benwunet.user.ui.adapter.UserReceivedAdapter;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserReceivedActivity
 * @Description: 我收到的名片
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */


public class UserReceivedActivity extends BaseActivity<ActivityUserCommonBinding, BaseViewModel> {

    private static final int REQUEST_CODE_INVITE =1001 ;
    private Context mContext;
    private UserReceivedAdapter adapter;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_common;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        adapter = new UserReceivedAdapter(R.layout.item_user_received_card, mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        final List<BaseCustomViewModel> data = new ArrayList<>();
        ThemesItemViewModel themesItemViewModel1 = new ThemesItemViewModel();
        themesItemViewModel1.job = "经理";
        themesItemViewModel1.state = "1";
        themesItemViewModel1.isSelect = true;
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
        themesItemViewModel.job = "副总";
        themesItemViewModel1.state = "3";
        themesItemViewModel.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        ThemesItemViewModel themesItemViewModel2 = new ThemesItemViewModel();
        themesItemViewModel2.job = "职员";
        themesItemViewModel1.state = "2";
        themesItemViewModel2.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel2);
        adapter.setNewData(data);
        binding.recyclerview.setAdapter(adapter);
        adapter.addChildClickViewIds(R.id.iv_delete);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK ) {
            if (data != null) {
                List<BaseCustomViewModel> data1 = (List<BaseCustomViewModel>) data.getSerializableExtra("data");
                adapter.setNewData(data1);
            }
        }
    }
}
