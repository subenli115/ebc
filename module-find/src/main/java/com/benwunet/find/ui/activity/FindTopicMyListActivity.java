package com.benwunet.find.ui.activity;

import android.content.Context;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.CommHttpActivity;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ActivityFindTopicHomeBinding;
import com.benwunet.find.ui.adapter.FindTopicHomeAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindTopicMyListActivity
 * @Description: 我的话题列表
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class FindTopicMyListActivity extends CommHttpActivity<ActivityFindTopicHomeBinding, FindViewModel> {
    private Context mContext;
    private String url = "http://zrwlmeiliao.oss-accelerate.aliyuncs.com/banner/xxx.png";
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }


    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mContext = this;
        baseBinding.ntb.setRightClose();
        baseBinding.ntb.setNewTitleText(getString(R.string.find_topic_me));
        final List<BaseCustomViewModel> data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel = new DynamicItemViewModel();
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        updateData(data);
    }


    @Override
    protected void loadData() {
//        viewModel.getCollectionCardList();
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new FindTopicHomeAdapter(R.layout.item_find_home_topic, this);
    }

    @Override
    public ViewBinding getHeader() {
        return null;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(FindTopicDetailsActivity.class);
    }
}