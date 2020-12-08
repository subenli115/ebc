package com.benwunet.cards.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.FragmentWorkBinding;
import com.benwunet.cards.ui.activity.CardsPaperActivity;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.jessyan.autosize.internal.CancelAdapt;
import me.jessyan.autosize.internal.CustomAdapt;


/**
 * Created by feng on 2020/10/15.
 */
@Route(path = RouterFragmentPath.Card.PAGER_CARD)
public class CardsFragment extends BaseFragment<FragmentWorkBinding, CardsViewModel> implements CancelAdapt{
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
        binding.igvCard.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                    startActivity(CardsPaperActivity.class);
            }
        });
        binding.igvCollection.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ARouter.getInstance().build(RouterActivityPath.Collection.PAGER_COLLECTION).navigation();
            }
        });
    }


}