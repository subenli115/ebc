package com.benwunet.find.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.base.viewmodel.BaseHttpViewModel;
import com.benwunet.base.base.viewmodel.IMvvmBaseViewModel;
import com.benwunet.base.global.ApiKey;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.ui.bean.MeCardCollectionBean;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.HttpManager;

import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by feng on 2020/10/15.
 */

public class FindViewModel extends BaseHttpViewModel  {

    public FindViewModel(@NonNull Application application) {
        super(application);
    }
    public SingleLiveEvent<MeCardCollectionBean> data = new SingleLiveEvent<>();
    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 6; i++) {
            observableList.add(new DynamicItemViewModel());
        }
    }

    public void getCollectionCardList() {
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
                                data.setValue(meCardCollectionBean);
                    }

                });
    }

    //给RecyclerView添加ItemBinding
    public ItemBinding<DynamicItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_find_city);
    //给RecyclerView添加ObservableList
    public ObservableList<DynamicItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<DynamicItemViewModel> itemBinding2 = ItemBinding.of(BR.viewModel, R.layout.item_find_city_contacts);


    @Override
    public void accept(Object o) throws Exception {

    }
}
