package com.benwunet.home.ui.respository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.bean.UploadFileBean;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.home.ui.bean.CardCreateBean;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.bean.CardStyleBean;
import com.benwunet.home.ui.viewmodel.HomeViewModel;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.home.ui.respository
 * @ClassName: MyCardRepository
 * @Description: 我的名片仓库
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:20
 * @Version: 1.0
 */


public class MyCardRepository extends BaseModel {

    private volatile static MyCardRepository instance = null;
    private final HomeViewModel viewModel;

    public MyCardRepository(HomeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static MyCardRepository getInstance(HomeViewModel viewModel) {
        if (instance == null) {
            synchronized (MyCardRepository.class) {
                if (instance == null) {
                    instance = new MyCardRepository(viewModel);

                }
            }
        }
        return instance;
    }

    public void getCardDetails(final MutableLiveData<CardDetailsBean> data, final MutableLiveData<CardDetailsBean.CompanyListBean> companyListBean) {
        HttpManager.get(ApiKey.MEMBER_CARD_MINE)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<CardDetailsBean>() {
                    @Override
                    public void onError(ApiException e) {
                    }

                    @Override
                    public void onSuccess(CardDetailsBean result) {
                        data.setValue(result);
                        companyListBean.setValue(result.getCompanyList().get(0));
                    }
                });

    }

    public void createCard(final MutableLiveData<CardCreateBean> data, final SingleLiveEvent<String> complete) {
        HttpManager.post(ApiKey.MEMBER_CARD)
                .accessToken()
                .upJson(GsonUtils.toJson(data.getValue()))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if(result){
                            ToastUtils.showLong("创建成功");
                            complete.call();
                        }
                    }
                });

    }

    public void editCard(final MutableLiveData<CardCreateBean> data, final SingleLiveEvent<String> complete) {
        HttpManager.put(ApiKey.MEMBER_CARD)
                .accessToken()
                .upJson(GsonUtils.toJson(data.getValue()))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if(result){
                            ToastUtils.showLong("修改成功");
                            complete.call();
                        }
                    }
                });
    }

    final UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
        @Override
        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
            int progress = (int) (bytesRead * 100 / contentLength);
            if (done) {//完成
                Log.e("ProgressCallBack", "finish");
            }
        }
    };

    public void uploadFile(String imgPath, String videoPath, final boolean isEdit, final MutableLiveData<CardCreateBean> createBean, final SingleLiveEvent<String> complete) {
        List<File> files = new ArrayList<>();
        if (imgPath != null && imgPath.length() > 0) {
            files.add(new File(imgPath));
        }
        if (videoPath != null && videoPath.length() > 0) {
            files.add(new File(videoPath));
        }
        HttpManager.post(ApiKey.OSS_FILE_ANON_MORE)
                .accessToken()
                .addFileParams("file", files, listener)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<List<UploadFileBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<UploadFileBean> result) {
                        CardCreateBean bean = createBean.getValue();
                        bean.setImagePhoto(result.get(0).getNetUrl());
                        if(result.size()==2){
                            bean.setShortVideo(result.get(1).getNetUrl());
                        }
                        if(isEdit){
                            editCard(createBean, complete);
                        }else {
                            createCard(createBean, complete);
                        }
                    }
                });
    }

    public void getCardStyle(final MutableLiveData<List<CardStyleBean>> styleList) {
        HttpManager.get(ApiKey.MEMBER_CARD_STYLE)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<List<CardStyleBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<CardStyleBean> result) {
                        styleList.setValue(result);
                    }
                });
    }


}
