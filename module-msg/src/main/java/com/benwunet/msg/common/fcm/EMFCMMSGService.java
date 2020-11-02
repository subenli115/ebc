package com.benwunet.msg.common.fcm;

import android.util.Log;

import com.benwunet.msg.DemoHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by zhangsong on 17-9-15.
 */
public class EMFCMMSGService extends FirebaseMessagingService {
    private static final String TAG = "EMFCMMSGService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            String message = remoteMessage.getData().get("alert");
            Log.i(TAG, "onMessageReceived: " + message);
            DemoHelper.getInstance().getNotifier().notify(message);
        }
    }
}
