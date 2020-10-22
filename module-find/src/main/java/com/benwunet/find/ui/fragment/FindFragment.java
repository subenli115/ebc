package com.benwunet.find.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.find.R;
import com.benwunet.find.BR;
import com.benwunet.find.databinding.FragmentFindBinding;
import com.benwunet.find.ui.viewmodel.FindViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;


/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Find.PAGER_FIND)
public class FindFragment extends BaseFragment<FragmentFindBinding, FindViewModel> {
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
    }
}
