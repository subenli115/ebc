package com.benwunet.msg.section.chat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.benwunet.msg.section.conversation.viewmodel.ConversationListViewModel;

public class ChatViewModel extends ConversationListViewModel {

    public ChatViewModel(@NonNull Application application) {
        super(application);
    }
}
