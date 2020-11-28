package com.benwunet.user.ui.respository;

import com.benwunet.base.bean.StringDataBean;
import com.benwunet.base.global.ApiKey;
import com.benwunet.base.utils.GsonUtils;
import com.benwunet.base.utils.MapUtils;
import com.benwunet.user.ui.bean.MeCardCollectionBean;
import com.benwunet.user.ui.bean.MeHomeBean;
import com.benwunet.user.ui.bean.MeInfoBean;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.user.ui.respository
 * @ClassName: InfoRepository
 * @Description: 个人首页仓库
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:20
 * @Version: 1.0
 */


public class MeRepository extends BaseModel {

    private volatile static MeRepository instance = null;
    private final BaseViewModel viewModel;

    public MeRepository(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static MeRepository getInstance(BaseViewModel viewModel) {
        if (instance == null) {
            synchronized (MeRepository.class) {
                if (instance == null) {
                    instance = new MeRepository(viewModel);

                }
            }
        }
        return instance;
    }


    public SingleLiveEvent<MeHomeBean> getMemberHome(final SingleLiveEvent<MeHomeBean> bean) {
        HttpManager.get(ApiKey.MEMBER_HOME)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<MeHomeBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(MeHomeBean result) {
                        bean.setValue(result);
                    }
                });
        return bean;
    }

    public void getMemberInfo(final SingleLiveEvent<MeInfoBean> info) {
        HttpManager.get(ApiKey.MEMBER_SETTINGS_PERSONAL)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<MeInfoBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(MeInfoBean result) {
                        info.setValue(result);

                    }
                });
    }

    public void editMemberInfo(final SingleLiveEvent<Boolean> complete, MeInfoBean meInfoBean) {
        HttpManager.put(ApiKey.MEMBER_SETTINGS_PERSONAL)
                .accessToken()
                .upJson(GsonUtils.toJson(MapUtils.transformBeanToMap(meInfoBean)))
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

    public void getCollectionCardList(final SingleLiveEvent<MeCardCollectionBean> data) {
        HttpManager.get(ApiKey.MEMBER_COLLECTION_CARD)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<MeCardCollectionBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(MeCardCollectionBean meCardCollectionBean) {

                    }

                });
    }

}
