package com.benwunet.msg.section.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.common.interfaceOrImplement.OnResourceParseCallback;
import com.benwunet.msg.section.chat.activity.ChatActivity;
import com.benwunet.msg.section.contact.adapter.PublicGroupContactAdapter;
import com.benwunet.msg.section.contact.viewmodels.PublicGroupViewModel;
import com.hyphenate.chat.EMGroup;
import com.benwunet.msg.R;
import com.hyphenate.easeui.adapter.EaseBaseRecyclerViewAdapter;

public class SearchPublicGroupActivity extends SearchActivity {

    private PublicGroupViewModel viewModel;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, SearchPublicGroupActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleBar.setTitle(getString(R.string.em_search_group_public));
        query.setHint(getString(R.string.em_search_group_public_hint));
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = new ViewModelProvider(this).get(PublicGroupViewModel.class);
        viewModel.getGroupObservable().observe(this, response -> {
            parseResource(response, new OnResourceParseCallback<EMGroup>() {
                @Override
                public void onSuccess(EMGroup data) {
                    adapter.addData(data);
                }
            });
        });
    }

    @Override
    protected EaseBaseRecyclerViewAdapter getAdapter() {
        return new SearchPublicGroupContactAdapter();
    }

    @Override
    public void searchMessages(String search) {
        if(!TextUtils.isEmpty(search)) {
            viewModel.getGroup(search);
        }
    }

    @Override
    protected void onChildItemClick(View view, int position) {
        EMGroup group = (EMGroup) adapter.getItem(position);
        ChatActivity.actionStart(mContext, group.getGroupId(), DemoConstant.CHATTYPE_GROUP);
    }

    private class SearchPublicGroupContactAdapter extends PublicGroupContactAdapter {
        @Override
        public int getEmptyLayoutId() {
            return R.layout.demo_layout_no_data_show_nothing;
        }
    }
}
