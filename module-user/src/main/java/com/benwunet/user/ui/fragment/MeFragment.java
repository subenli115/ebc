package com.benwunet.user.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.FragmentMeBinding;
import com.benwunet.user.ui.activity.UserAboutActivity;
import com.benwunet.user.ui.activity.UserCollectionActivity;
import com.benwunet.user.ui.bean.MeHomeBean;
import com.benwunet.user.ui.viewmodel.MeViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;


/**
 *
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
        binding.igvCollection.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserCollectionActivity.class);
            }
        });

        binding.igvAbout.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserAboutActivity.class);
            }
        });
    }

    @Override
    public void initViewObservable() {
        viewModel.homeBean.observe(this, new Observer<MeHomeBean>() {
            @Override
            public void onChanged(MeHomeBean meHomeBean) {
                if(meHomeBean.getReceiveCardNum()>0){
                    binding.tvCardNum.setVisibility(View.VISIBLE);
                }
                if(meHomeBean.getRecentVisitorNum()>0){
                    binding.tvVisitorNum.setVisibility(View.VISIBLE);
                }
                if(meHomeBean.isIsCreateCard()){
                    binding.ivCode.setVisibility(View.VISIBLE);
                    binding.rlInfo.setVisibility(View.VISIBLE);
                }else {
                    binding.tvCreateCard.setVisibility(View.VISIBLE);
                }
                viewModel.setHomeEntity(meHomeBean);

            }
        });
    }
}
