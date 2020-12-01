package com.benwunet.base.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benwunet.base.R;
import com.benwunet.base.databinding.FragmentBaseCommonBinding;
import com.benwunet.base.model.BaseViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;


public abstract class CommonFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<V, VM> implements OnItemClickListener {

    public BaseQuickAdapter mAdapter;
    private FragmentBaseCommonBinding baseBinding;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstceState) {
        return R.layout.fragment_base_common;
    }

    @Override
    public void initData() {
        super.initData();
        baseBinding = (FragmentBaseCommonBinding) binding;
        loadData();
        initRecyclerView();
    }

    /**
     * 加载数据
     */
    protected abstract void loadData();

    private void initRecyclerView() {
        baseBinding.recyclerview.setHasFixedSize(true);
        baseBinding.recyclerview.setNestedScrollingEnabled(false);
        baseBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter();
        if (baseBinding.recyclerview.getAdapter() == null) {
            baseBinding.recyclerview.setAdapter(mAdapter);

        }
        mAdapter.setOnItemClickListener(this);

    }

    public void updateData(List data) {
        mAdapter.setNewData(data);
    }

    ;

    /**
     * 构建适配器
     *
     * @return 适配器
     */
    public abstract BaseQuickAdapter getAdapter();

}
