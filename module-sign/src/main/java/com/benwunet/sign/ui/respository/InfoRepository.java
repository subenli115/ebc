package com.benwunet.sign.ui.respository;

import com.benwunet.base.bean.StringDataBean;
import com.benwunet.base.bean.UploadFileBean;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.global.IConstants;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.sign.ui.bean.CompleteInfoBean;
import com.benwunet.sign.ui.bean.IndustryListBean;
import com.benwunet.sign.ui.bean.TopicBean;
import com.benwunet.sign.ui.bean.jobListBean;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.io.File;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.StringUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.sign.ui.respository
 * @ClassName: InfoRepository
 * @Description: 完善资料仓库
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:20
 * @Version: 1.0
 */


public class InfoRepository extends BaseModel {

    private volatile static InfoRepository instance = null;
    private final InfoViewModel viewModel;

    public InfoRepository(InfoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static InfoRepository getInstance(InfoViewModel viewModel) {
        if (instance == null) {
            synchronized (InfoRepository.class) {
                if (instance == null) {
                    instance = new InfoRepository(viewModel);

                }
            }
        }
        return instance;
    }


    public void getPosition(final SingleLiveEvent<List<jobListBean>> job) {
        HttpManager.get(ApiKey.SYS_POSITION)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<List<jobListBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<jobListBean> result) {
                        job.setValue(result);
                    }
                });
    }

    public void getIndustry(final SingleLiveEvent<List<IndustryListBean>> industry) {
        HttpManager.get(ApiKey.SYS_INDUSTRY)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<List<IndustryListBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<IndustryListBean> result) {
                        industry.setValue(result);
                    }
                });
    }

    public void getTopic(final SingleLiveEvent<TopicBean> topic, String type) {
        String url = "";
        if (StringUtils.equals(type, IConstants.HOTTOPIC)) {
            url = ApiKey.CIRCLE_TOPIC_HOT;
        } else {
            url = ApiKey.CIRCLE_TOPIC;
        }
        HttpManager.get(url)
                .accessToken()
                .cacheMode(CacheMode.FIRSTCACHE)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<TopicBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(TopicBean result) {
                        topic.setValue(result);
                    }
                });
    }


    public void completeInfo(CompleteInfoBean entity, final SingleLiveEvent<Boolean> complete) {
        HttpManager.put(ApiKey.MEMBER_COMPLETE_INFORMATION)
                .accessToken()
                .upJson(GsonUtils.toJson(MapUtils.transformBeanToMap(entity)))
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        StringDataBean bean = GsonUtils.fromLocalJson(result, StringDataBean.class);
                        if (bean.getCode() == 0) {
                            complete.setValue(true);
                        }
                    }
                });
    }

    final UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
        @Override
        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
            int progress = (int) (bytesRead * 100 / contentLength);
            if (done) {//完成

            }
        }
    };



    public void uploadFile(final CompleteInfoBean bean, final SingleLiveEvent<Boolean> complete) {
            HttpManager.post(ApiKey.OSS_FILE_ANON)
                    .accessToken()
                    .params("file", new File(bean.getAvatar()), listener)
                    .cacheKey(this.getClass().getSimpleName())
                    .execute(new SimpleCallBack<UploadFileBean>() {
                        @Override
                        public void onError(ApiException e) {

                            ToastUtils.showLong(e.getMessage());
                        }

                        @Override
                        public void onSuccess(UploadFileBean result) {
                            bean.setAvatar(result.getNetUrl());
                            completeInfo(bean, complete);

                        }
                    });
    }

}
