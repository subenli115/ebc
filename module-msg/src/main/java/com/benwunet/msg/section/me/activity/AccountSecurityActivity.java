package com.benwunet.msg.section.me.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.benwunet.msg.R;
import com.benwunet.msg.common.widget.ArrowItemView;
import com.benwunet.msg.section.base.BaseInitActivity;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class AccountSecurityActivity extends BaseInitActivity implements EaseTitleBar.OnBackPressListener, View.OnClickListener {
    private EaseTitleBar titleBar;
    private ArrowItemView itemEquipments;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AccountSecurityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_account_security;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleBar = findViewById(R.id.title_bar);
        itemEquipments = findViewById(R.id.item_equipments);
    }

    @Override
    protected void initListener() {
        super.initListener();
        titleBar.setOnBackPressListener(this);
        itemEquipments.setOnClickListener(this);
    }

    @Override
    public void onBackPress(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.item_equipments) {//多端多设备管理
            MultiDeviceActivity.actionStart(mContext);
        }
    }
}
