package com.benwunet.find.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.base.fragment.CommonFragment;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.FragmentFindCommonBinding;
import com.benwunet.find.ui.adapter.FindMeetingAdapter;
import com.benwunet.find.ui.adapter.FindRecommendAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @Package: com.benwunet.find.ui.fragment
 * @ClassName: FindMeetingFragment
 * @Description: 会议主页fragment
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */


public class FindMeetingFragment extends CommonFragment<FragmentFindCommonBinding, FindViewModel> {


    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        final List<BaseCustomViewModel> data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel = new DynamicItemViewModel();
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        updateData(data);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return  new FindMeetingAdapter(R.layout.item_find_meeting, getContext());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
