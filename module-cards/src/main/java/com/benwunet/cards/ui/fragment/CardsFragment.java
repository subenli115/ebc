package com.benwunet.cards.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.cards.R;
import com.benwunet.cards.BR;
import com.benwunet.cards.databinding.FragmentWorkBinding;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;


/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Card.PAGER_CARD)
public class CardsFragment extends BaseFragment<FragmentWorkBinding, CardsViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_work;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    }
}
