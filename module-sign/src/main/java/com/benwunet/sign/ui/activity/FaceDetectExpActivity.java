package com.benwunet.sign.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baidu.idl.face.platform.FaceStatusNewEnum;
import com.baidu.idl.face.platform.model.ImageInfo;
import com.baidu.idl.face.platform.ui.CollectionSuccessActivity;
import com.baidu.idl.face.platform.ui.FaceDetectActivity;
import com.baidu.idl.face.platform.ui.utils.IntentUtils;
import com.benwunet.sign.ui.wdiget.TimeoutDialog;

import java.util.HashMap;

public class FaceDetectExpActivity extends FaceDetectActivity implements
        TimeoutDialog.OnTimeoutDialogClickListener {

    private TimeoutDialog mTimeoutDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加至销毁列表
//        ExampleApplication.addDestroyActivity(FaceDetectExpActivity.this,
//                "FaceDetectExpActivity");
    }

    @Override
    public void onDetectCompletion(FaceStatusNewEnum status, String message,
                                   HashMap<String, ImageInfo> base64ImageCropMap,
                                   HashMap<String, ImageInfo> base64ImageSrcMap) {
        super.onDetectCompletion(status, message, base64ImageCropMap, base64ImageSrcMap);
        if (status == FaceStatusNewEnum.OK && mIsCompletion) {
            // showMessageDialog("人脸图像采集", "采集成功");
            IntentUtils.getInstance().setBitmap(mBmpStr);
            Intent intent = new Intent(FaceDetectExpActivity.this,
                    CollectionSuccessActivity.class);
            intent.putExtra("destroyType", "FaceDetectExpActivity");
            startActivity(intent);
        } else if (status == FaceStatusNewEnum.DetectRemindCodeTimeout) {
            if (mViewBg != null) {
                mViewBg.setVisibility(View.VISIBLE);
            }
            showMessageDialog();
        }
    }

    private void showMessageDialog() {
        mTimeoutDialog = new TimeoutDialog(this);
        mTimeoutDialog.setDialogListener(this);
        mTimeoutDialog.setCanceledOnTouchOutside(false);
        mTimeoutDialog.setCancelable(false);
        mTimeoutDialog.show();
        onPause();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onRecollect() {
        if (mTimeoutDialog != null) {
            mTimeoutDialog.dismiss();
        }
        if (mViewBg != null) {
            mViewBg.setVisibility(View.GONE);
        }
        onResume();
    }

    @Override
    public void onReturn() {
        if (mTimeoutDialog != null) {
            mTimeoutDialog.dismiss();
        }
        finish();
    }
}
