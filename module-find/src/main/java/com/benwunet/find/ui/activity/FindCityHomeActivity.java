package com.benwunet.find.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ActivityFindCityBinding;
import com.benwunet.find.ui.viewmodel.FindViewModel;


/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindCityHomeActivity
 * @Description: 城市主页
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class FindCityHomeActivity extends BaseActivity<ActivityFindCityBinding, FindViewModel> {

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_find_city;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.ntb.setOnRightImagListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
            }
        });
        binding.ivHead.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindCityDetailsActivity.class);
            }
        });

    }



}