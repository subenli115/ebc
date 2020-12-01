package com.benwunet.find.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.FragmentFindNearbyBinding;
import com.benwunet.find.databinding.ItemFindTopicFootViewBinding;
import com.benwunet.find.ui.adapter.FindDynamicAdapter;
import com.benwunet.find.ui.adapter.FindNearbyAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @Package: com.benwunet.find.ui.fragment
 * @ClassName: FindNearbyFragment
 * @Description: 发现主页附近
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */


public class FindNearbyFragment extends BaseFragment<FragmentFindNearbyBinding, FindViewModel> {
    List<BaseCustomViewModel> data;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find_nearby;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        FindDynamicAdapter adapter1 = new FindDynamicAdapter(R.layout.item_find_dynamic, getContext());
        FindNearbyAdapter adapter2 = new FindNearbyAdapter(R.layout.item_find_nearby, getContext());
        data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel1 = new DynamicItemViewModel();
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel1);
        initAdapter(adapter1, binding.recyclerview1, "更多附近的人");
        initAdapter(adapter2, binding.recyclerview2, "更多附近的会议");

    }

    private void initAdapter(BaseQuickAdapter adapter, RecyclerView recyclerView, String title) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setNewData(data);
        ItemFindTopicFootViewBinding footViewBinding = ItemFindTopicFootViewBinding.inflate(LayoutInflater.from(getContext()));
        footViewBinding.tvTitle.setText(title);
        adapter.setFooterView(footViewBinding.getRoot());
        recyclerView.setAdapter(adapter);
    }

}
