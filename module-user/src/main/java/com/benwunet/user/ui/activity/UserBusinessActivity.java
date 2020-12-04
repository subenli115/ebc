package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserBusinessBinding;
import com.benwunet.user.ui.adapter.UserBusinessAdapter;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserBusinessActivity
 * @Description: 我的企业
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */


public class UserBusinessActivity extends BaseActivity<ActivityUserBusinessBinding, BaseViewModel> {

    private static final int REQUEST_CODE_INVITE =1001 ;
    private Context mContext;
    private UserBusinessAdapter adapter;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_business;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.ntb.setOnRightTextListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(mContext, UserManageActivity.class);
                intent.putExtra("data", (Serializable) adapter.getData());
                startActivityForResult(intent, REQUEST_CODE_INVITE);
            }
        });
        adapter = new UserBusinessAdapter(R.layout.item_user_business_view, mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        final List<BaseCustomViewModel> data = new ArrayList<>();
        ThemesItemViewModel themesItemViewModel1 = new ThemesItemViewModel();
        themesItemViewModel1.title = "本无网络科技有限公司";
        themesItemViewModel1.job = "经理";
        themesItemViewModel1.isSelect = true;
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
        themesItemViewModel.title = "小本网络有限公司";
        themesItemViewModel.job = "副总";
        themesItemViewModel.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        ThemesItemViewModel themesItemViewModel2 = new ThemesItemViewModel();
        themesItemViewModel2.title = "小本网络有限公司";
        themesItemViewModel2.job = "职员";
        themesItemViewModel2.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel2);
        binding.tvNum.setText("(共" + data.size() + "家)");
        adapter.setNewData(data);
        binding.recyclerview.setAdapter(adapter);

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
