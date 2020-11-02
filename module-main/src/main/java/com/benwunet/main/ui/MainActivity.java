package com.benwunet.main.ui;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.main.BR;
import com.benwunet.main.R;
import com.benwunet.main.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * Created by feng on 2020/10/15
 */
//@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        Fragment cardFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Card.PAGER_CARD).navigation();
        Fragment findFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Find.PAGER_FIND).navigation();
        Fragment msgFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Msg.PAGER_MSG).navigation();
        Fragment meFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.User.PAGER_ME).navigation();
        mFragments = new ArrayList<>();
        mFragments.add(homeFragment);
        mFragments.add(cardFragment);
        mFragments.add(findFragment);
        mFragments.add(msgFragment);
        mFragments.add(meFragment);
        if (homeFragment != null) {
            //默认选中第一个
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout, homeFragment);
            transaction.commitAllowingStateLoss();
        }
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.yingyong, "我的名片")
                .addItem(R.mipmap.huanzhe, "名片夹")
                .addItem(R.mipmap.huanzhe, "发现")
                .addItem(R.mipmap.xiaoxi_select, "消息")
                .addItem(R.mipmap.wode_select, "个人中心")
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                Fragment currentFragment = mFragments.get(index);
                if (currentFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayout, currentFragment);
                    transaction.commitAllowingStateLoss();
                }
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }
}
