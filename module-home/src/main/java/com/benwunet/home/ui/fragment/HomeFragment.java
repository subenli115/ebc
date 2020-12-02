package com.benwunet.home.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.FragmentHomeBinding;
import com.benwunet.home.ui.viewmodel.HomeViewModel;
import com.lzj.gallery.library.views.BannerViewPager;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements CustomAdapt {
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private ArrayList<String> urlList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
//        urlList = new ArrayList<>();
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543221773&di=c63f30c7809e518cafbff961bcd9ec2a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0116605851154fa8012060c8587ca1.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542627042541&di=3ad9deeefff266e76d1f5d57a58f63d1&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F69%2F99%2F66%2F9fce5755f081660431464492a9aeb003.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542627042539&di=95bd41d43c335e74863d9bb540361906&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019a0558be22d6a801219c77d0578a.jpg%402o.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586013578881&di=3fe60f355e63e0b15aa59e6f3597b2a8&imgtype=0&src=http%3A%2F%2Fphoto.16pic.com%2F00%2F34%2F07%2F16pic_3407117_b.jpg");
//        binding.banner2.initBanner(urlList, true)//关闭3D画廊效果
//                .addPageMargin(10, 50)//参数1page之间的间距,参数2中间item距离边界的间距
//
//                .addPointMargin(0)//添加指示器
//                .addPointBottom(-71)
//                .addRoundCorners(20)//圆角
//                .finishConfig()//这句必须加
//                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
//                    @Override
//                    public void onBannerClick(int position) {
//
//                    }
//                });

    }

    @Override
    public void initViewObservable() {
        viewModel.itemClickEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ToastUtils.showShort(s);
            }
        });
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 555;
    }
}
