package com.benwunet.msg.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.benwunet.msg.BR;
import com.benwunet.msg.R;
import com.benwunet.msg.databinding.FragmentMsgBinding;
import com.benwunet.msg.ui.viewmodel.MsgViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;


/**
 * Created by feng on 2020/10/15.
 */
//@Route(path = RouterFragmentPath.Msg.PAGER_MSG)
public class MsgFragment extends BaseFragment<FragmentMsgBinding, MsgViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_msg;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    }
}
