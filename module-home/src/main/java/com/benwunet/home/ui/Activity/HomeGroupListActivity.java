package com.benwunet.home.ui.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeSelectGroupBinding;
import com.benwunet.home.ui.adapter.HomeGroupChatAdapter;
import com.benwunet.home.ui.bean.HomeGroupListBean;
import com.benwunet.home.ui.viewmodel.GroupListViewModel;

import java.util.List;

/**
 * @Package: com.benwunet.home.ui.activity
 * @ClassName: HomeGroupListActivity
 * @Description: 选择群列表
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */
public class HomeGroupListActivity extends BaseActivity<ActivityHomeSelectGroupBinding, GroupListViewModel> {

    private HomeGroupChatAdapter adapter;
    private Context mContext;

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home_select_group;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        adapter = new HomeGroupChatAdapter(R.layout.item_company_industry_view, mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerview.setAdapter(adapter);
        viewModel.homeGroupList.observe(this, new Observer<List<HomeGroupListBean>>() {
            @Override
            public void onChanged(List<HomeGroupListBean> list) {
                adapter.setNewData(list);
            }
        });

    }


    @Override
    public void initViewObservable() {
        viewModel.finish.observe(this, new Observer() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(Object o) {
                finish();
            }
        });
    }

}
