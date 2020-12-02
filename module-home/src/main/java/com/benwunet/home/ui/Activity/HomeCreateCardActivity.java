package com.benwunet.home.ui.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeCreateCardBinding;
import com.benwunet.home.ui.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeCreateCardActivity extends BaseActivity<ActivityHomeCreateCardBinding, HomeViewModel> {
    private Context mContext;
    private List<View> viewList;
    private List<View> bgViewList;
    private String type;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home_create_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        binding.tvCreate.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        mContext = this;
        if(getIntent()!=null){
             type = getIntent().getStringExtra("type");
        }
        if(type!=null&&type.equals("edit")){
            binding.ntb.setNewTitleText(getString(R.string.base_edit_card));
            binding.tvCreate.setText("保存");
        }
        viewList = new ArrayList<>();
        viewList.add(binding.ivCustomSelect);
        viewList.add(binding.ivCitySelect);
        viewList.add(binding.ivSelfSelect);
        viewList.add(binding.ivVipSelect);

        bgViewList = new ArrayList<>();
        bgViewList.add(binding.ivCustom);
        bgViewList.add(binding.ivCity);
        bgViewList.add(binding.ivSelf);
        bgViewList.add(binding.ivVip);
        initClick();
    }

    public void setVisib(int position) {
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).setVisibility(View.GONE);
        }
        viewList.get(position).setVisibility(View.VISIBLE);
    }

    public void initClick() {
        for (int i = 0; i < bgViewList.size(); i++) {
            final int finalI = i;
            bgViewList.get(i).setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    setVisib(finalI);
                }
            });
        }
    }

}

