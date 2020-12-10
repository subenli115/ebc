package com.benwunet.cards.respository;

import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.global.ApiKey;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.cards.ui.bean.CardPaperBean;
import com.benwunet.cards.ui.bean.CardsHomeBean;
import com.benwunet.cards.ui.bean.CardsMindGroupListBean;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.cards.ui.CardsRepository
 * @ClassName: InfoRepository
 * @Description: 个人首页仓库
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 11:20
 * @Version: 1.0
 */


public class CardsRepository extends BaseModel {

    private volatile static CardsRepository instance = null;
    private final BaseViewModel viewModel;

    public CardsRepository(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static CardsRepository getInstance(BaseViewModel viewModel) {
        if (instance == null) {
            synchronized (CardsRepository.class) {
                if (instance == null) {
                    instance = new CardsRepository(viewModel);

                }
            }
        }
        return instance;
    }


    public void getMemberCardPaper(final MutableLiveData<List<CardPaperBean>> listMutableLiveData) {
        HttpManager.get(ApiKey.MEMBER_CARD_PAPER)
                .accessToken()
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<List<CardPaperBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<CardPaperBean> result) {
                        listMutableLiveData.setValue(result);
                    }
                });
    }
    public void getHomeData(final MutableLiveData<CardsHomeBean> listMutableLiveData, final MutableLiveData<List<CardsHomeBean.GroupsBean>> groups) {
        HttpManager.get(ApiKey.MEMBER_GROUP_HOLDER)
                .accessToken()
                .cacheMode(CacheMode.FIRSTCACHE)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<CardsHomeBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(CardsHomeBean result) {
                        listMutableLiveData.setValue(result);
                        groups.setValue(result.getGroups());
                    }
                });
    }
    public void getTypeGroupList(final MutableLiveData<CardsMindGroupListBean> data, String type) {
        HttpManager.get(ApiKey.MEMBER_GROUP+type)
                .accessToken()
                .cacheMode(CacheMode.FIRSTCACHE)
                .cacheKey(this.getClass().getSimpleName())
                .execute(new SimpleCallBack<CardsMindGroupListBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onSuccess(CardsMindGroupListBean result) {
                        data.setValue(result);
                    }
                });
    }


}
