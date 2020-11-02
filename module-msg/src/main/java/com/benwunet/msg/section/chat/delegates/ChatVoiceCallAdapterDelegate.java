package com.benwunet.msg.section.chat.delegates;

import android.view.View;
import android.view.ViewGroup;

import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.section.chat.viewholder.ChatVoiceCallViewHolder;
import com.benwunet.msg.section.chat.views.ChatRowVoiceCall;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.delegate.EaseMessageAdapterDelegate;
import com.hyphenate.easeui.interfaces.MessageListItemClickListener;
import com.hyphenate.easeui.model.styles.EaseMessageListItemStyle;
import com.hyphenate.easeui.viewholder.EaseChatRowViewHolder;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;

import static com.hyphenate.chat.EMMessage.Type.TXT;

public class ChatVoiceCallAdapterDelegate extends EaseMessageAdapterDelegate<EMMessage, EaseChatRowViewHolder> {
    @Override
    public boolean isForViewType(EMMessage item, int position) {
        return item.getType() == TXT && item.getBooleanAttribute(DemoConstant.MESSAGE_ATTR_IS_VOICE_CALL, false);
    }

    @Override
    protected EaseChatRow getEaseChatRow(ViewGroup parent, boolean isSender) {
        return new ChatRowVoiceCall(parent.getContext(), isSender);
    }

    @Override
    protected EaseChatRowViewHolder createViewHolder(View view, MessageListItemClickListener itemClickListener, EaseMessageListItemStyle itemStyle) {
        return new ChatVoiceCallViewHolder(view, itemClickListener, itemStyle);
    }
}
