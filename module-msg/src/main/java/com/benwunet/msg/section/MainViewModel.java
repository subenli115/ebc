package com.benwunet.msg.section;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.msg.DemoHelper;
import com.benwunet.msg.common.db.DemoDbHelper;
import com.benwunet.msg.common.db.dao.InviteMessageDao;
import com.benwunet.msg.common.livedatas.LiveDataBus;
import com.benwunet.msg.common.livedatas.SingleSourceLiveData;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {
    private InviteMessageDao inviteMessageDao;
    private SingleSourceLiveData<Integer> switchObservable;
    private MutableLiveData<String> homeUnReadObservable;

    public MainViewModel(@NonNull Application application) {
        super(application);
        switchObservable = new SingleSourceLiveData<>();
        inviteMessageDao = DemoDbHelper.getInstance(application).getInviteMessageDao();
        homeUnReadObservable = new MutableLiveData<>();
    }

    public LiveData<Integer> getSwitchObservable() {
        return switchObservable;
    }

    /**
     * 设置可见的fragment
     * @param title
     */
    public void setVisibleFragment(Integer title) {
        switchObservable.setValue(title);
    }

    public LiveData<String> homeUnReadObservable() {
        return homeUnReadObservable;
    }

    public LiveDataBus messageChangeObservable() {
        return LiveDataBus.get();
    }

    public void checkUnreadMsg() {
        int unreadCount = inviteMessageDao.queryUnreadCount();
        int unreadMessageCount = DemoHelper.getInstance().getChatManager().getUnreadMessageCount();
        String count = getUnreadCount(unreadCount + unreadMessageCount);
        homeUnReadObservable.postValue(count);
    }

    /**
     * 获取未读消息数目
     * @param count
     * @return
     */
    private String getUnreadCount(int count) {
        if(count <= 0) {
            return null;
        }
        if(count > 99) {
            return "99+";
        }
        return String.valueOf(count);
    }

}
