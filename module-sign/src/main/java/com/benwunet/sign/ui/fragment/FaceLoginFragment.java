package com.benwunet.sign.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.FragmentSignFaceBinding;
import com.benwunet.sign.ui.viewmodel.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * @Package: com.benwunet.sign.ui.fragment
 * @ClassName: FaceLoginActivity
 * @Description: 密码登录
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */


public class FaceLoginFragment extends BaseFragment<FragmentSignFaceBinding, LoginViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_sign_face;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        initLicense();
    }
    private void initLicense() {
//        setFaceConfig();
//        // 为了android和ios 区分授权，appId=appname_face_android ,其中appname为申请sdk时的应用名
//        // 应用上下文
//        // 申请License取得的APPID
//        // assets目录下License文件名
//        FaceSDKManager.getInstance().initialize(getActivity(), Config.licenseID,Config.licenseFileName,new IInitCallback() {
//            @Override
//            public void initSuccess() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        KLog.e("initSuccess","initSuccess");
//                    }
//                });
//            }
//
//            @Override
//            public void initFailure(final int errCode, final String errMsg) {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        KLog.e("initSuccess",errMsg);
//                    }
//                });
//            }
//        });
    }
    /**
     * 参数配置方法
     */
    private void setFaceConfig() {
        FaceConfig config = FaceSDKManager.getInstance().getFaceConfig();
        // SDK初始化已经设置完默认参数（推荐参数），也可以根据实际需求进行数值调整
        // 设置可检测的最小人脸阈值
        config.setMinFaceSize(FaceEnvironment.VALUE_MIN_FACE_SIZE);
        // 设置可检测到人脸的阈值
        config.setNotFaceValue(FaceEnvironment.VALUE_NOT_FACE_THRESHOLD);
        // 设置模糊度阈值
        config.setBlurnessValue(FaceEnvironment.VALUE_BLURNESS);
        // 设置光照阈值（范围0-255）
        config.setBrightnessValue(FaceEnvironment.VALUE_BRIGHTNESS);
        // 设置遮挡阈值
        config.setOcclusionValue(FaceEnvironment.VALUE_OCCLUSION);
        // 设置人脸姿态角阈值
        config.setHeadPitchValue(FaceEnvironment.VALUE_HEAD_PITCH);
        config.setHeadYawValue(FaceEnvironment.VALUE_HEAD_YAW);
        // 设置闭眼阈值
        config.setEyeClosedValue(FaceEnvironment.VALUE_CLOSE_EYES);
        // 设置图片缓存数量
        config.setCacheImageNum(FaceEnvironment.VALUE_CACHE_IMAGE_NUM);
        // 设置口罩判断开关以及口罩阈值
        config.setOpenMask(FaceEnvironment.VALUE_OPEN_MASK);
        config.setMaskValue(FaceEnvironment.VALUE_MASK_THRESHOLD);
        // 设置活体动作，通过设置list，LivenessTypeEunm.Eye, LivenessTypeEunm.Mouth,
        // LivenessTypeEunm.HeadUp, LivenessTypeEunm.HeadDown, LivenessTypeEunm.HeadLeft,
        // LivenessTypeEunm.HeadRight, LivenessTypeEunm.HeadLeftOrRight
        // 设置动作活体是否随机
        config.setLivenessRandom(false);
        // 设置开启提示音
        config.setSound(true);
        // 原图缩放系数
        config.setScale(FaceEnvironment.VALUE_SCALE);
        // 抠图高的设定，为了保证好的抠图效果，我们要求高宽比是4：3，所以会在内部进行计算，只需要传入高即可
        config.setCropHeight(FaceEnvironment.VALUE_CROP_HEIGHT);
        // 抠图人脸框与背景比例
        config.setEnlargeRatio(FaceEnvironment.VALUE_CROP_ENLARGERATIO);
        // 加密类型，0：Base64加密，上传时image_sec传false；1：百度加密文件加密，上传时image_sec传true
        config.setSecType(FaceEnvironment.VALUE_SEC_TYPE);
        FaceSDKManager.getInstance().setFaceConfig(config);

    }
}
