package com.benwunet.user.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.FragmentMeBinding;
import com.benwunet.user.ui.viewmodel.MeViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;


/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.User.PAGER_ME)
public class MeFragment extends BaseFragment<FragmentMeBinding, MeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.userInfoEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
    }
}
