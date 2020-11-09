package com.benwunet.sign.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.idl.face.platform.FaceStatusNewEnum;
import com.baidu.idl.face.platform.model.ImageInfo;
import com.baidu.idl.face.platform.ui.FaceDetectActivity;
import com.baidu.idl.face.platform.ui.utils.IntentUtils;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.sign.ui.bean.UserLoginBean;
import com.benwunet.sign.ui.wdiget.TimeoutDialog;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: FaceDetectExpActivity
 * @Description: 人脸扫描页面
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 15:13
 * @Version: 1.0
 */
public class FaceDetectExpActivity extends FaceDetectActivity implements
        TimeoutDialog.OnTimeoutDialogClickListener {

    private TimeoutDialog mTimeoutDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加至销毁列表
    }

    @Override
    public void onDetectCompletion(FaceStatusNewEnum status, String message,
                                   HashMap<String, ImageInfo> base64ImageCropMap,
                                   HashMap<String, ImageInfo> base64ImageSrcMap) {
        super.onDetectCompletion(status, message, base64ImageCropMap, base64ImageSrcMap);
        if (status == FaceStatusNewEnum.OK && mIsCompletion) {
            // showMessageDialog("人脸图像采集", "采集成功");
            IntentUtils.getInstance().setBitmap(mBmpStr);
            faceLogin();
        } else if (status == FaceStatusNewEnum.DetectRemindCodeTimeout) {
            if (mViewBg != null) {
                mViewBg.setVisibility(View.VISIBLE);
            }
            showMessageDialog();
        }
    }

    public void faceLogin() {
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("faceBase64",mBmpStr);
        defMap.put("token","100861");
        HttpManager.post(ApiKey.OAUTH_FACE)
                .headers("client_id","app")
                .headers("client_secret","123456")
                .upJson(GsonUtils.toJson(defMap))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<UserLoginBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                        finish();
                    }

                    @Override
                    public void onSuccess(UserLoginBean result) {
                        ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                    }

                });
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
