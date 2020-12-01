package com.benwunet.find.ui.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ActivityFindTopicDetailsBinding;
import com.benwunet.find.databinding.ItemFindTopicHeadViewBinding;
import com.benwunet.find.ui.adapter.FindDetailsTabPagerAdapter;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.google.android.material.tabs.TabLayout;


/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindTopicDetailsActivity
 * @Description: 话题详情
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class FindTopicDetailsActivity extends BaseActivity<ActivityFindTopicDetailsBinding, FindViewModel> {
    private Context mContext;
    private ItemFindTopicHeadViewBinding headBinding;
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_find_topic_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        initTabFragment();
    }

    private void initTabFragment() {
        mContext = this;
        binding.ntb.setOnRightImagListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                View mMenuView = getLayoutInflater().inflate(R.layout.pop_find_topic_details, null);
                final PopupWindow pop =new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                mMenuView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if(v.getId()==R.id.rl_first){

                        }else {

                        }
                        pop.dismiss();
                    }
                });
                pop.showAsDropDown(v);
            }
        });
        FindDetailsTabPagerAdapter adapter = new FindDetailsTabPagerAdapter(getSupportFragmentManager(), mContext);
        binding.vp.setAdapter(adapter);
        binding.tl.setupWithViewPager(binding.vp);
        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = binding.tl.getTabAt(i);
            tab.setCustomView(R.layout.tab_title);
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                TextView tv = tab.getCustomView().findViewById(android.R.id.text1);
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.textColorYellow));
            }
        }
        binding.tl.setTabRippleColor(ColorStateList.valueOf(mContext.getResources().getColor(R.color.transparent)));
        binding.tl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 获取 tab 组件
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view) {
                    // 改变 tab 选择状态下的字体大小
                    // 改变 tab 选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.textColorYellow));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view) {
                    // 改变 tab 未选择状态下的字体大小
                    // 改变 tab 未选择状态下的字体颜色
                    ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.sign_textColor_normal));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}