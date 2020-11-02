package com.benwunet.msg.section.contact.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.benwunet.msg.common.livedatas.SingleSourceLiveData;
import com.benwunet.msg.common.net.Resource;
import com.benwunet.msg.common.repositories.EMGroupManagerRepository;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupOptions;

public class NewGroupViewModel extends AndroidViewModel {
    private EMGroupManagerRepository repository;
    private SingleSourceLiveData<Resource<EMGroup>> groupObservable;

    public NewGroupViewModel(@NonNull Application application) {
        super(application);
        repository = new EMGroupManagerRepository();
        groupObservable = new SingleSourceLiveData<>();
    }

    public LiveData<Resource<EMGroup>> groupObservable() {
        return groupObservable;
    }

    public void createGroup(String groupName, String desc, String[] allMembers, String reason, EMGroupOptions option) {
        groupObservable.setSource(repository.createGroup(groupName, desc, allMembers, reason, option));
    }
}
