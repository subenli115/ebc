package com.benwunet.cards.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.ActivityCardsCommonGroupBinding;
import com.benwunet.cards.ui.adapter.CardsCommonGroupAdapter;
import com.benwunet.cards.ui.bean.CardsMindGroupListBean;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import me.goldze.mvvmhabit.base.AppManager;

/**
 * @Package: com.benwunet.cards.ui.activity
 * @ClassName: CardsCommonGroupingActivity
 * @Description: 公用智能分组子分组
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class CardsCommonGroupingActivity extends BaseActivity<ActivityCardsCommonGroupBinding, CardsViewModel> {

    private Context mContext;
    private CardsCommonGroupAdapter adapter;
    private String title;

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards_common_group;
    }


    public static void startAction(String type) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, CardsCommonGroupingActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mContext = this;
        if (getIntent() != null) {
            String type = getIntent().getStringExtra("type");
            viewModel.initTypeGroupData(type);
            if (type == IConstants.LOCATION) {
                title = getString(R.string.base_location);
            } else if (type == IConstants.POSITION) {
                title = getString(R.string.base_job2);
            } else {
                title = getString(R.string.base_industry);
            }

        }
        binding.ntb.setNewTitleText(title);
        binding.ntb.setTypeTitleText(getString(R.string.base_mind_ground));
        initAdapter();
    }

    private void initAdapter() {
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setNestedScrollingEnabled(false);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CardsCommonGroupAdapter(R.layout.item_cards_mind_group, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(CardsPagerDetailsActivity.class);
            }
        });
        binding.recyclerview.setAdapter(adapter);

    }

    @Override
    public void initViewObservable() {
        viewModel.cardsMindGroupList.observe(this, new Observer<CardsMindGroupListBean>() {
            @Override
            public void onChanged(CardsMindGroupListBean cardsMindGroupListBean) {

            }
        });
    }
}