package com.benwunet.find.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ActivityFindCityDetailsBinding;
import com.benwunet.find.ui.adapter.FindRecommendAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindCityHomeActivity
 * @Description: 城市详情
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */

public class FindCityDetailsActivity extends BaseActivity<ActivityFindCityDetailsBinding, FindViewModel> {
    private Context mContext;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_find_city_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.rvSecond.setHasFixedSize(true);
        binding.rvSecond.setNestedScrollingEnabled(false);
        binding.rvSecond.setLayoutManager(new LinearLayoutManager(this));
        FindRecommendAdapter adapter = new FindRecommendAdapter(R.layout.item_find_recommend, this);
        List<BaseCustomViewModel> data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel = new DynamicItemViewModel();
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        adapter.setNewData(data);
        binding.tvContacts.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindCityContactsActivity.class);
            }
        });

        binding.tvDynamic.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindCityDynamicListActivity.class);
            }
        });
        binding.rvSecond.setAdapter(adapter);
    }



}