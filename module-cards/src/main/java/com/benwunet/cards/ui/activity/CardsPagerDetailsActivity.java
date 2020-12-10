package com.benwunet.cards.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.cards.BR;
import com.benwunet.cards.R;
import com.benwunet.cards.databinding.ActivityCardsDetailsPagerBinding;
import com.benwunet.cards.databinding.ActivityCardsEditPagerBinding;
import com.benwunet.cards.ui.viewmodel.CardsViewModel;

/**
 * @Package: com.benwunet.cards.ui.activity
 * @ClassName: CardsPagerEditActivity
 * @Description: 纸质卡片详情
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class CardsPagerDetailsActivity extends BaseActivity<ActivityCardsDetailsPagerBinding, CardsViewModel> {

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cards_details_pager;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        String result = getIntent().getStringExtra("result");

    }




}
