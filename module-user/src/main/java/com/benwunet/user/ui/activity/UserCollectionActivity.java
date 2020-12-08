package com.benwunet.user.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.utils.CommonUtils;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserCollectionBinding;
import com.benwunet.user.ui.adapter.UserViewPagerBindingAdapter;
import com.benwunet.user.ui.viewmodel.ViewPagerViewModel;
import com.google.android.material.tabs.TabLayout;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserCollectionActivity
 * @Description: 我的收藏
 * @Author: feng
 * @CreateDate: 2020/11/18 0018 11:14
 * @Version: 1.0
 */

@Route(path = RouterActivityPath.Collection.PAGER_COLLECTION)
public class UserCollectionActivity extends BaseActivity<ActivityUserCollectionBinding, ViewPagerViewModel> {
    private Context mContext;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_collection;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext=this;
        String[] stringArray = getResources().getStringArray(R.array.user_collection);
        LinearLayout linearLayout = (LinearLayout) binding.tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding((int)CommonUtils.dp2px(mContext,16));
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.tablayout_divider_vertical));
        viewModel.initData(stringArray);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        //给ViewPager设置adapter

        binding.setAdapter(new UserViewPagerBindingAdapter(mContext));
    }

    @Override
    public void initViewObservable() {

    }
}