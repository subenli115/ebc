package com.benwunet.msg.section.contact.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.benwunet.msg.common.livedatas.SingleSourceLiveData;
import com.benwunet.msg.common.net.Resource;
import com.benwunet.msg.common.repositories.EMContactManagerRepository;

public class ContactDetailViewModel extends AndroidViewModel {
    private EMContactManagerRepository repository;
    private SingleSourceLiveData<Resource<Boolean>> deleteObservable;
    private SingleSourceLiveData<Resource<Boolean>> blackObservable;

    public ContactDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new EMContactManagerRepository();
        deleteObservable = new SingleSourceLiveData<>();
        blackObservable = new SingleSourceLiveData<>();
    }

    public LiveData<Resource<Boolean>> deleteObservable() {
        return deleteObservable;
    }

    public LiveData<Resource<Boolean>> blackObservable() {
        return blackObservable;
    }

    public void deleteContact(String username) {
        deleteObservable.setSource(repository.deleteContact(username));
    }

    public void addUserToBlackList(String username, boolean both) {
        blackObservable.setSource(repository.addUserToBlackList(username, both));
    }

}
