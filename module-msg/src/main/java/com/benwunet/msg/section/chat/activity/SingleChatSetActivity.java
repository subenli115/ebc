package com.benwunet.msg.section.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.common.interfaceOrImplement.OnResourceParseCallback;
import com.benwunet.msg.common.livedatas.LiveDataBus;
import com.benwunet.msg.common.widget.ArrowItemView;
import com.benwunet.msg.common.widget.SwitchItemView;
import com.benwunet.msg.section.base.BaseInitActivity;
import com.benwunet.msg.section.chat.viewmodel.ChatViewModel;
import com.benwunet.msg.section.contact.activity.ContactDetailActivity;
import com.benwunet.msg.section.dialog.DemoDialogFragment;
import com.benwunet.msg.section.dialog.SimpleDialogFragment;
import com.benwunet.msg.section.search.SearchSingleChatActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.benwunet.msg.R;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class SingleChatSetActivity extends BaseInitActivity implements EaseTitleBar.OnBackPressListener, View.OnClickListener, SwitchItemView.OnCheckedChangeListener {
    private EaseTitleBar titleBar;
    private ArrowItemView itemUserInfo;
    private ArrowItemView itemSearchHistory;
    private ArrowItemView itemClearHistory;
    private SwitchItemView itemSwitchTop;

    private ChatViewModel viewModel;
    private String toChatUsername;
    private EMConversation conversation;

    public static void actionStart(Context context, String toChatUsername) {
        Intent intent = new Intent(context, SingleChatSetActivity.class);
        intent.putExtra("toChatUsername", toChatUsername);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_single_chat_set;
    }

    @Override
    protected void initIntent(Intent intent) {
        super.initIntent(intent);
        toChatUsername = getIntent().getStringExtra("toChatUsername");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleBar = findViewById(R.id.title_bar);
        itemUserInfo = findViewById(R.id.item_user_info);
        itemSearchHistory = findViewById(R.id.item_search_history);
        itemClearHistory = findViewById(R.id.item_clear_history);
        itemSwitchTop = findViewById(R.id.item_switch_top);
    }

    @Override
    protected void initListener() {
        super.initListener();
        titleBar.setOnBackPressListener(this);
        itemUserInfo.setOnClickListener(this);
        itemSearchHistory.setOnClickListener(this);
        itemClearHistory.setOnClickListener(this);
        itemSwitchTop.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        conversation = EMClient.getInstance()
                                                .chatManager()
                                                .getConversation(toChatUsername, EaseCommonUtils.getConversationType(EaseConstant.CHATTYPE_SINGLE), true);
        itemUserInfo.getAvatar().setShapeType(1);
        itemUserInfo.getTvTitle().setText(toChatUsername);
        itemSwitchTop.getSwitch().setChecked(!TextUtils.isEmpty(conversation.getExtField()));

        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        viewModel.getDeleteObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.CONVERSATION_DELETE).postValue(new EaseEvent(DemoConstant.CONTACT_DECLINE, EaseEvent.TYPE.MESSAGE));
                    finish();
                }
            });
        });

    }

    @Override
    public void onBackPress(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.item_user_info) {
            EaseUser user = new EaseUser();
            user.setUsername(toChatUsername);
            ContactDetailActivity.actionStart(mContext, user);
        } else if (id == R.id.item_search_history) {
            SearchSingleChatActivity.actionStart(mContext, toChatUsername);
        } else if (id == R.id.item_clear_history) {
            clearHistory();
        }
    }

    private void clearHistory() {
        // 是否删除会话
        new SimpleDialogFragment.Builder(mContext)
                .setTitle(R.string.em_chat_delete_conversation)
                .setOnConfirmClickListener(new DemoDialogFragment.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(View view) {
                        viewModel.deleteConversationById(conversation.conversationId());
                    }
                })
                .showCancelButton(true)
                .show();
    }

    @Override
    public void onCheckedChanged(SwitchItemView buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.item_switch_top) {
            conversation.setExtField(isChecked ? (System.currentTimeMillis() + "") : "");
            LiveDataBus.get().with(DemoConstant.MESSAGE_CHANGE_CHANGE).postValue(new EaseEvent(DemoConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.TYPE.MESSAGE));
        }
    }
}
