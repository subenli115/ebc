package com.benwunet.cards.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.ActivityCardsGroupingBinding;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;

/**
 * @Package: com.benwunet.cards.ui.activity
 * @ClassName: IntelligentGroupingActivity
 * @Description: 智能分组
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class IntelligentGroupingActivity extends BaseActivity<ActivityCardsGroupingBinding, CardsViewModel> {

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards_grouping;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.igvJob.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CardsCommonGroupingActivity.startAction(IConstants.POSITION);
            }
        });
        binding.igvIndustry.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CardsCommonGroupingActivity.startAction(IConstants.INDUSTRY);
            }
        });
        binding.igvLocation.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CardsCommonGroupingActivity.startAction(IConstants.LOCATION);
            }
        });
    }

}
