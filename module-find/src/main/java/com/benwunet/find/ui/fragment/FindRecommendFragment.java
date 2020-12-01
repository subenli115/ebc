package com.benwunet.find.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.FragmentFindCommonBinding;
import com.benwunet.find.ui.adapter.FindRecommendAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @Package: com.benwunet.find.ui.fragment
 * @ClassName: FindRecommendFragment
 * @Description: 发现主页推荐
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */


public class FindRecommendFragment extends BaseFragment<FragmentFindCommonBinding, FindViewModel> {
    List<BaseCustomViewModel> data;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find_common;
    }
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setNestedScrollingEnabled(false);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FindRecommendAdapter adapter = new FindRecommendAdapter(R.layout.item_find_recommend, getContext());
         data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel = new DynamicItemViewModel();
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        adapter.setNewData(data);
        binding.recyclerview.setAdapter(adapter);

    }
}
