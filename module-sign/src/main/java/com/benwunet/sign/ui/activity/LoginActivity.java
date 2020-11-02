package com.benwunet.sign.ui.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityLoginBinding;
import com.benwunet.sign.ui.adapter.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 一个MVVM模式的登陆界面
 * 作为登录验证模块的路由页
 */
@Route(path = RouterActivityPath.Sign.PAGER_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, BaseViewModel>{
    private LoginActivity mContext;

    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        initTabFragment();
    }

    @Override
    public void initData() {
        mContext = this;
    }

    private void initTabFragment() {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(),mContext);
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
                    ((TextView) view).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.textColorYellow));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView().findViewById(android.R.id.text1);
                if (null != view ) {
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