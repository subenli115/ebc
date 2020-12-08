package com.benwunet.home.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.config.GlideEngine;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeCreateCardBinding;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.viewmodel.HomeViewModel;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.List;
/**
 * @Package: com.benwunet.home.ui.activity
 * @ClassName: HomeCompanyIndustryActivity
 * @Description: 创建或编辑名片
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */
public class HomeCreateCardActivity extends BaseActivity<ActivityHomeCreateCardBinding, HomeViewModel> {
    private Context mContext;
    private List<View> viewList;
    private List<View> bgViewList;
    private String type;
    private String path;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home_create_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        viewModel.cardDetailsBeanMutableLiveData.observe(this, new Observer<CardDetailsBean>() {
            @Override
            public void onChanged(CardDetailsBean cardDetailsBean) {
                viewModel.initViewData(cardDetailsBean);
                Glide.with(mContext).load(cardDetailsBean.getImagePhoto()).into(binding.ivBg);
            }
        });
        binding.tvCreate.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (type != null && type.equals("edit")) {
                    viewModel.editAndCreateCard(true);
                } else {
                    viewModel.editAndCreateCard(false);
                }
            }
        });

        viewModel.complete.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        binding.llPhoto.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                selectBg(PictureMimeType.ofImage());
            }
        });
        binding.llVideo.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                selectBg(PictureMimeType.ofVideo());
            }
        });
    }

    private void selectBg(final int type) {
        PictureSelector.create(HomeCreateCardActivity.this)
                .openGallery(type)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .isEnableCrop(true)
                .cropImageWideHigh(750,750)
                .selectionMode(PictureConfig.SINGLE)
                .withAspectRatio(1, 1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        if (type == PictureMimeType.ofImage()) {
                            Glide.with(mContext).load(result.get(0).getCutPath()).into(binding.ivBg);
                        }
                        viewModel.setPath(type == PictureMimeType.ofVideo()?result.get(0).getRealPath():result.get(0).getCutPath(), type == PictureMimeType.ofVideo());
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    @Override
    public void initData() {
        mContext = this;
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
        }
        if (type != null && type.equals("edit")) {
            binding.ntb.setNewTitleText(getString(R.string.base_edit_card));
            binding.tvCreate.setText("保存");
        }
        viewModel.getStyleList();
        viewModel.getHomeData();
        viewList = new ArrayList<>();
        viewList.add(binding.ivCustomSelect);
        viewList.add(binding.ivCitySelect);
        viewList.add(binding.ivSelfSelect);
        viewList.add(binding.ivVipSelect);

        bgViewList = new ArrayList<>();
        bgViewList.add(binding.ivCustom);
        bgViewList.add(binding.ivCity);
        bgViewList.add(binding.ivSelf);
        bgViewList.add(binding.ivVip);
        initClick();
    }

    public void setVisib(int position) {
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).setVisibility(View.GONE);
        }
        viewModel.setSelectNum(position);
        viewList.get(position).setVisibility(View.VISIBLE);

    }

    public void initClick() {
        for (int i = 0; i < bgViewList.size(); i++) {
            final int finalI = i;
            bgViewList.get(i).setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    setVisib(finalI);
                }
            });
        }

        binding.tvIndustry.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(HomeCompanyIndustryActivity.class);
            }
        });
    }

}

