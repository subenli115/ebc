package com.benwunet.msg.section.group.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.benwunet.msg.DemoHelper;
import com.benwunet.msg.R;
import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.common.interfaceOrImplement.OnResourceParseCallback;
import com.benwunet.msg.common.widget.ArrowItemView;
import com.benwunet.msg.section.base.BaseInitActivity;
import com.benwunet.msg.section.group.GroupHelper;
import com.benwunet.msg.section.group.viewmodels.GroupMemberAuthorityViewModel;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.widget.EaseTitleBar;

import java.util.List;
import java.util.Map;

public class GroupManageIndexActivity extends BaseInitActivity implements View.OnClickListener, EaseTitleBar.OnBackPressListener {
    private EaseTitleBar titleBar;
    private ArrowItemView itemBlackManager;
    private ArrowItemView itemMuteManage;
    private Button btnTransfer;
    private String groupId;

    public static void actionStart(Context context, String groupId) {
        Intent intent = new Intent(context, GroupManageIndexActivity.class);
        intent.putExtra("groupId", groupId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_group_manage;
    }

    @Override
    protected void initIntent(Intent intent) {
        super.initIntent(intent);
        groupId = intent.getStringExtra("groupId");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleBar = findViewById(R.id.title_bar);
        itemBlackManager = findViewById(R.id.item_black_manager);
        itemMuteManage = findViewById(R.id.item_mute_manage);
        btnTransfer = findViewById(R.id.btn_transfer);

        btnTransfer.setVisibility(GroupHelper.isOwner(DemoHelper.getInstance().getGroupManager().getGroup(groupId)) ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initListener() {
        super.initListener();
        titleBar.setOnBackPressListener(this);
        itemBlackManager.setOnClickListener(this);
        itemMuteManage.setOnClickListener(this);
        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.item_black_manager) {//黑名单
            GroupMemberAuthorityActivity.actionStart(mContext, groupId, GroupMemberAuthorityActivity.TYPE_BLACK);
        } else if (id == R.id.item_mute_manage) {//禁言列表
            GroupMemberAuthorityActivity.actionStart(mContext, groupId, GroupMemberAuthorityActivity.TYPE_MUTE);
        } else if (id == R.id.btn_transfer) {//移交
            GroupTransferActivity.actionStart(mContext, groupId);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        GroupMemberAuthorityViewModel viewModel = new ViewModelProvider(this).get(GroupMemberAuthorityViewModel.class);
        viewModel.getMuteMembersObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Map<String, Long>>() {
                @Override
                public void onSuccess(Map<String, Long> data) {
                    itemMuteManage.getTvContent().setText(data.size() + "个");
                }
            });
        });
        viewModel.getBlackObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<List<String>>() {
                @Override
                public void onSuccess(List<String> data) {
                    itemBlackManager.getTvContent().setText(data.size() + "个");
                }

            });
        });
        viewModel.getMessageChangeObservable().with(DemoConstant.GROUP_CHANGE, EaseEvent.class).observe(this, event -> {
            if(TextUtils.equals(event.event, DemoConstant.GROUP_OWNER_TRANSFER)) {
                finish();
                return;
            }
            if(event.isGroupChange()) {
                viewModel.getBlackMembers(groupId);
                viewModel.getMuteMembers(groupId);
            }
        });
        viewModel.getBlackMembers(groupId);
        viewModel.getMuteMembers(groupId);
    }

    @Override
    public void onBackPress(View view) {
        onBackPressed();
    }
}
