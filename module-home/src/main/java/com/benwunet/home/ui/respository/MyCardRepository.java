package com.benwunet.home.ui.respository;

import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.global.ApiKey;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.viewmodel.HomeViewModel;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

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

    public void getCardDetails(final MutableLiveData<CardDetailsBean> data) {
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
                    }
                });

    }


}
