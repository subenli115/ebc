package com.benwunet.msg.section.me.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.benwunet.msg.BuildConfig;
import com.benwunet.msg.R;
import com.benwunet.msg.common.widget.ArrowItemView;
import com.benwunet.msg.section.base.BaseInitActivity;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class AboutHxActivity extends BaseInitActivity implements View.OnClickListener, EaseTitleBar.OnBackPressListener {
    private EaseTitleBar title_bar;
    private TextView tv_version;
    private ArrowItemView item_product;
    private ArrowItemView item_company;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AboutHxActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_about_hx;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        title_bar = findViewById(R.id.title_bar);
        tv_version = findViewById(R.id.tv_version);
        item_product = findViewById(R.id.item_product);
        item_company = findViewById(R.id.item_company);
    }

    @Override
    protected void initData() {
        super.initData();
        tv_version.setText(getString(R.string.em_about_hx_version, BuildConfig.VERSION_NAME));
    }

    @Override
    protected void initListener() {
        super.initListener();
        title_bar.setOnBackPressListener(this);
        item_product.setOnClickListener(this);
        item_company.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.item_product) {
            jumpToIMIntroduction();
        } else if (id == R.id.item_company) {
            jumpToCompanyIntroduction();
        }
    }

    private void jumpToIMIntroduction() {
        Uri uri = Uri.parse("http://www.easemob.com/product/im");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    private void jumpToCompanyIntroduction() {
        Uri uri = Uri.parse("http://www.easemob.com/about");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @Override
    public void onBackPress(View view) {
        onBackPressed();
    }
}
