package com.benwunet.find.ui.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.GlideRoundTransform;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.R;
import com.benwunet.find.BR;
import com.benwunet.find.databinding.FragmentFindBinding;
import com.benwunet.find.ui.activity.FindCityHomeActivity;
import com.benwunet.find.ui.activity.FindMettingActivity;
import com.benwunet.find.ui.activity.FindTopicHomeActivity;
import com.benwunet.find.ui.adapter.FindTabPagerAdapter;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;


/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Find.PAGER_FIND)
public class FindFragment extends BaseFragment<FragmentFindBinding, FindViewModel> {

    private Context mContext;
    private String url = "http://zrwlmeiliao.oss-accelerate.aliyuncs.com/banner/xxx.png";
    private FindTabPagerAdapter adapter;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find;
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


    public void setBinnerData() {
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
        mContext = getContext();
        binding.llTopic.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindTopicHomeActivity.class);
            }
        });
        binding.llCity.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindCityHomeActivity.class);
            }
        });
        binding.llMeeting.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindMettingActivity.class);
            }
        });
            adapter = new FindTabPagerAdapter(getChildFragmentManager(), mContext);
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
