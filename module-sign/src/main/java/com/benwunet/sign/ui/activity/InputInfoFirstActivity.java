package com.benwunet.sign.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityInputInfoFirstBinding;
import com.benwunet.sign.ui.config.GlideEngine;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: InputInfoFirstActivity
 * @Description: 完善资料页面1
 * @Author: feng
 * @CreateDate: 2020/10/22 0022 18:21
 * @Version: 1.0
 */


public class InputInfoFirstActivity extends BaseActivity<ActivityInputInfoFirstBinding, InfoViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_input_info_first;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        viewModel.isSelectPhoto.observe(this, aBoolean -> PictureSelector.create(InputInfoFirstActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .isEnableCrop(true)
                .withAspectRatio(1,1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        viewModel.imgUrl.setValue(result.get(0).getCutPath());
                        Log.e("getCutPath",""+result.get(0).getCutPath());
                        binding.ivAdd.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancel() {

                    }
                }));
    }

    @Override
    public void initData() {
        binding.setLifecycleOwner(this);
    }

}
