package com.benwunet.find.ui.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ActivityFindMeetingBinding;
import com.benwunet.find.databinding.FragmentFindBinding;
import com.benwunet.find.ui.adapter.FindMeetingTabPagerAdapter;
import com.benwunet.find.ui.adapter.FindTabPagerAdapter;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;
import me.goldze.mvvmhabit.base.BaseFragment;


/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindMettingActivity
 * @Description: 会议主页
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class FindMettingActivity extends BaseActivity<ActivityFindMeetingBinding, FindViewModel> {
    private Context mContext;
    private String url="http://zrwlmeiliao.oss-accelerate.aliyuncs.com/banner/xxx.png";


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_find_meeting;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        initTabFragment();
        setBinnerData();
    }

    public  void setBinnerData(){
        binding.banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                RequestOptions roundOptions = new RequestOptions()
                        .transform(new RoundedCorners(10));
                Glide.with(mContext)
                        .load(model)
                        .apply(roundOptions)
                        .into(itemView);
            }
        });
        binding.banner.setData(Arrays.asList(url, url, url), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));
    }

    private void initTabFragment() {
        mContext = this;
        FindMeetingTabPagerAdapter adapter = new FindMeetingTabPagerAdapter(this.getSupportFragmentManager(), mContext);
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
