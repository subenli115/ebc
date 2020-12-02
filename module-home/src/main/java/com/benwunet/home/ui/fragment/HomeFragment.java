package com.benwunet.home.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.FragmentHomeBinding;
import com.benwunet.home.ui.Activity.HomeCreateCardActivity;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.viewmodel.HomeViewModel;
import com.lzj.gallery.library.views.BannerViewPager;

import java.util.ArrayList;

import me.goldze.mvvmhabit.utils.ToastUtils;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CancelAdapt;
import me.jessyan.autosize.internal.CustomAdapt;

import static android.app.Activity.RESULT_OK;

/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private static final int CREATE_CODE = 0;

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
        binding.tvCreate.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(getContext(), HomeCreateCardActivity.class);
                startActivityForResult(intent, CREATE_CODE);
            }
        });
        binding.llEdit.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(getContext(), HomeCreateCardActivity.class);
                intent.putExtra("type","edit");
                startActivityForResult(intent, CREATE_CODE);
            }
        });
    }


    @Override
    public void initViewObservable() {
        viewModel.cardDetailsBeanMutableLiveData.observe(this, new Observer<CardDetailsBean>() {
            @Override
            public void onChanged(CardDetailsBean cardDetailsBean) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            binding.tvCreate.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            binding.tvHint.setVisibility(View.GONE);
            binding.llBottomContainer.setVisibility(View.VISIBLE);
            binding.llInfo.setVisibility(View.VISIBLE);
        }

    }
}
