package com.benwunet.msg.section.conversation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.benwunet.msg.DemoHelper;
import com.benwunet.msg.R;
import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.common.db.entity.MsgTypeManageEntity;
import com.benwunet.msg.common.interfaceOrImplement.OnResourceParseCallback;
import com.benwunet.msg.common.livedatas.LiveDataBus;
import com.benwunet.msg.common.net.Resource;
import com.benwunet.msg.common.utils.ToastUtils;
import com.benwunet.msg.section.base.BaseActivity;
import com.benwunet.msg.section.chat.activity.ChatActivity;
import com.benwunet.msg.section.chat.viewmodel.MessageViewModel;
import com.benwunet.msg.section.conversation.delegate.SystemMessageDelegate;
import com.benwunet.msg.section.conversation.viewmodel.ConversationListViewModel;
import com.benwunet.msg.section.message.SystemMsgsActivity;
import com.benwunet.msg.section.search.SearchConversationActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseSearchTextView;

import java.util.List;


public class ConversationListFragment extends EaseConversationListFragment implements View.OnClickListener {
    private EaseSearchTextView tvSearch;

    private ConversationListViewModel mViewModel;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //添加搜索会话布局
//        viewStub.setLayoutResource(R.layout.demo_layout_search);
//        View view = viewStub.inflate();
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        tvSearch = view.findViewById(R.id.tv_search);

        initViewModel();
    }

    @Override
    public void addDelegate() {
        super.addDelegate();
        listAdapter.addDelegate(new SystemMessageDelegate());
    }

    @Override
    public void onChildCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo, Object item) {
        super.onChildCreateContextMenu(menu, v, menuInfo, item);
        if(item instanceof MsgTypeManageEntity) {
            String ext = ((MsgTypeManageEntity) item).getExtField();
            if(!TextUtils.isEmpty(ext) && EaseCommonUtils.isTimestamp(ext)) {
                // 含有时间戳
                menu.findItem(R.id.action_cancel_top).setVisible(true);
                menu.findItem(R.id.action_make_top).setVisible(false);
            }
        }
    }

    @Override
    public void onChildContextItemSelected(MenuItem menuItem, Object object) {
        super.onChildContextItemSelected(menuItem, object);
        if(object instanceof MsgTypeManageEntity) {
            MsgTypeManageEntity msg = (MsgTypeManageEntity) object;
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_make_top) {
                msg.setExtField(System.currentTimeMillis() + "");
                DemoHelper.getInstance().update(msg);
                mViewModel.loadConversationList();
            } else if (itemId == R.id.action_cancel_top) {
                msg.setExtField("");
                DemoHelper.getInstance().update(msg);
                mViewModel.loadConversationList();
            } else if (itemId == R.id.action_delete) {
                mViewModel.deleteSystemMsg(msg);
            }
        }
    }

    @Override
    public void initListener() {
        super.initListener();
//        tvSearch.setOnClickListener(this);
    }

    @Override
    public void refreshList() {
        mViewModel.loadConversationList();
    }

    @Override
    public void makeConversationRead(EMConversation conversation) {
        mViewModel.makeConversationRead(conversation.conversationId());
    }

    @Override
    public void deleteConversation(String conversationId) {
        mViewModel.deleteConversationById(conversationId);
    }

    private void initViewModel() {
//        mViewModel = new ViewModelProvider(this).get(ConversationListViewModel.class);
        mViewModel = ViewModelProviders.of(this).get(ConversationListViewModel.class);
        mViewModel.getConversationObservable().observe(getViewLifecycleOwner(), response -> {
            parseResource(response, new OnResourceParseCallback<List<Object>>() {
                @Override
                public void onSuccess(List<Object> data) {
                    listAdapter.setData(data);
                }

                @Override
                public void hideLoading() {
                    super.hideLoading();
                    finishRefresh();
                }
            });

        });

        mViewModel.getDeleteObservable().observe(getViewLifecycleOwner(), response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.MESSAGE_CHANGE_CHANGE).postValue(new EaseEvent(DemoConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.TYPE.MESSAGE));
                    mViewModel.loadConversationList();
                }
            });
        });

        mViewModel.getReadObservable().observe(getViewLifecycleOwner(), response -> {
            parseResource(response, new OnResourceParseCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    LiveDataBus.get().with(DemoConstant.MESSAGE_CHANGE_CHANGE).postValue(new EaseEvent(DemoConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.TYPE.MESSAGE));
                    mViewModel.loadConversationList();
                }
            });
        });

        MessageViewModel messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        LiveDataBus messageChange = messageViewModel.getMessageChange();
        messageChange.with(DemoConstant.NOTIFY_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.GROUP_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.CHAT_ROOM_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.CONVERSATION_DELETE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.CONTACT_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(DemoConstant.MESSAGE_CALL_SAVE, Boolean.class).observe(getViewLifecycleOwner(), this::refreshList);
        messageChange.with(DemoConstant.MESSAGE_NOT_SEND, Boolean.class).observe(getViewLifecycleOwner(), this::refreshList);
    }

    private void refreshList(Boolean event) {
        if(event == null) {
            return;
        }
        if(event) {
            mViewModel.loadConversationList();
        }
    }

    private void loadList(EaseEvent change) {
        if(change == null) {
            return;
        }
        if(change.isMessageChange() || change.isNotifyChange()
                || change.isGroupLeave() || change.isChatRoomLeave()
                || change.isContactChange()
                || change.type == EaseEvent.TYPE.CHAT_ROOM || change.isGroupChange()) {
            mViewModel.loadConversationList();
        }
    }

    /**
     * 解析Resource<T>
     * @param response
     * @param callback
     * @param <T>
     */
    public <T> void parseResource(Resource<T> response, @NonNull OnResourceParseCallback<T> callback) {
        if(mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).parseResource(response, callback);
        }
    }

    /**
     * toast by string
     * @param message
     */
    public void showToast(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_search) {
            SearchConversationActivity.actionStart(mContext);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        Object item = listAdapter.getItem(position);
        if(item instanceof EMConversation) {
            ChatActivity.actionStart(mContext, ((EMConversation)item).conversationId(), EaseCommonUtils.getChatType((EMConversation) item));
        }else if(item instanceof MsgTypeManageEntity) {
            SystemMsgsActivity.actionStart(mContext);
        }
    }
}
