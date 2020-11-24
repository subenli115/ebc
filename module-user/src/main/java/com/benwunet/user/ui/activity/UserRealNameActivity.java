package com.benwunet.user.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.config.GlideEngine;
import com.benwunet.base.wdiget.BottomMenuDialog;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserRealNameBinding;
import com.benwunet.user.ui.viewmodel.SettingViewModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserRealNameActivity
 * @Description: 用户实名认证
 * @Author: feng
 * @CreateDate: 2020/11/14 0014 09:59
 * @Version: 1.0
 */


public class UserRealNameActivity extends BaseActivity<ActivityUserRealNameBinding, SettingViewModel> {
    private Context mContext;
    private BottomMenuDialog mItemDialog;
    private BottomMenuDialog mDialog;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_real_name;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        binding.tvNext.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
                startActivity(UserRealInfoActivity.class);
            }
        });

        binding.llUserCardPositive.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPhotoDialog(mContext, v);
            }
        });
    }

    public void showPhotoDialog(final Context context, final View v) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        mDialog = new BottomMenuDialog(context);
        mDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //拍照
                PictureSelector.create(UserRealNameActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .isEnableCrop(true)
                        .withAspectRatio(1, 1)
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                Drawable drw1 = Drawable.createFromPath(result.get(0).getCutPath());
                                v.setBackground(drw1);
                            }

                            @Override
                            public void onCancel() {

                            }
                        });

            }

        });
        mDialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //相册
                PictureSelector.create(UserRealNameActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .isEnableCrop(true)
                        .withAspectRatio(1, 1)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                Drawable drw1 = Drawable.createFromPath(result.get(0).getCutPath());
                                v.setBackground(drw1);

                            }

                            @Override
                            public void onCancel() {

                            }
                        });

            }
        });
        mDialog.show();
    }
}