package com.benwunet.user.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.benwunet.base.base.activity.CommHttpActivity;
import com.benwunet.base.base.viewmodel.BaseHttpViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserFollowBinding;
import com.benwunet.user.ui.adapter.UserFansAdapter;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;


/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserFansActivity
 * @Description: 我的粉丝
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */


public class UserFansActivity extends CommHttpActivity<ActivityUserFollowBinding, BaseHttpViewModel> {
    private Context mContext;
    private ArrayList<ThemesItemViewModel> data;

    //拿到路由过来的参数



    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mContext = this;
        data = new ArrayList<>();
        ThemesItemViewModel themesItemViewModel1 = new ThemesItemViewModel();
        themesItemViewModel1.title = "杨幂";
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
        themesItemViewModel.title = "张赞";
        themesItemViewModel.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        ThemesItemViewModel themesItemViewModel2 = new ThemesItemViewModel();
        themesItemViewModel1.title = "杨幂";
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel2);
        initView();
        updateData(data);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new UserFansAdapter(R.layout.item_user_fans_view, mContext);
    }

    @Override
    public ViewBinding getHeader() {
        return null;
    }

    private void initView() {
        baseBinding.ntb.setNewTitleText(getString(R.string.user_fans));
        baseBinding.ntb.setOnRightImagListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                baseBinding.ntb.getIvRight().setVisibility(View.GONE);
                baseBinding.ntb.getRightTextView().setVisibility(View.VISIBLE);
                for(int i=0;i<data.size();i++){
                    ThemesItemViewModel themesItemViewModel = data.get(i);
                    themesItemViewModel.isVisible=true;
                    themesItemViewModel.isSelect=false;
                    mAdapter.setData(i,themesItemViewModel);
                }
            }
        });
        mAdapter.addChildClickViewIds(R.id.iv_select);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                ThemesItemViewModel bean = (ThemesItemViewModel) adapter.getData().get(position);
                bean.isSelect = !bean.isSelect;
                adapter.setData(position, bean);

            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
