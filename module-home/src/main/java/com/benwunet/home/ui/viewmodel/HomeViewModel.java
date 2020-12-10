package com.benwunet.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.model.BaseViewModel;
import com.benwunet.home.ui.bean.CardCreateBean;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.bean.CardStyleBean;
import com.benwunet.home.ui.bean.HomeGroupListBean;
import com.benwunet.home.ui.bean.RepresentativeBean;
import com.benwunet.home.ui.respository.MyCardRepository;

import java.util.List;

import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;


/**
 * Created by feng on 2020/10/15.
 */

public class HomeViewModel extends BaseViewModel {
    public MutableLiveData<CardDetailsBean> cardDetailsBeanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<CardDetailsBean.CompanyListBean> companyListBean = new MutableLiveData<>();
    public MutableLiveData<CardCreateBean> createBean = new MutableLiveData<>();
    public MutableLiveData<List<CardStyleBean>> styleList = new MutableLiveData<>();
    public MutableLiveData<String> imgUrl = new MutableLiveData<>();
    public SingleLiveEvent<String> complete = new SingleLiveEvent<>();
    public MutableLiveData<String> videoUrl = new MutableLiveData<>();
    public MutableLiveData<RepresentativeBean> representativeBean = new MutableLiveData<>();
    private MyCardRepository repository = MyCardRepository.getInstance(this);
    private boolean mIsVideo;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cardDetailsBeanMutableLiveData.setValue(new CardDetailsBean());
        createBean.setValue(new CardCreateBean());
    }
    public void getHomeData() {
        repository.getCardDetails(cardDetailsBeanMutableLiveData, companyListBean);
    }

    public void editAndCreateCard(boolean isEdit) {
        if (imgUrl.getValue() != null || videoUrl.getValue() != null) {
            repository.uploadFile(imgUrl.getValue(), videoUrl.getValue(), isEdit, createBean,complete);
        } else {
            CardCreateBean bean = createBean.getValue();
            if(bean.getRealName().length()==0||bean.getMobile().length()==0||bean.getAddressInfo().length()==0){
                ToastUtils.showLong("请填写正确信息");
                return;
            }
            if (isEdit) {
                repository.editCard(createBean,complete);
            } else {
                repository.createCard(createBean,complete);
            }
        }
    }

    public void getStyleList() {

        repository.getCardStyle(styleList);
    }

    /**
     * 存放styleId
     */
    public void setSelectNum(int position) {
        CardCreateBean bean = createBean.getValue();
        String styleId = styleList.getValue().get(position).getStyleId();
        bean.setStyleId(styleId);
    }
    /**
     * 存放styleId
     */
    public void setPath(String path, boolean isVideo) {
        mIsVideo = isVideo;
        if (isVideo) {
            videoUrl.setValue(path);
        } else {
            imgUrl.setValue(path);
        }
    }

    public void initViewData(CardDetailsBean cardDetailsBean) {
        CardCreateBean bean = createBean.getValue();
        bean.setRealName(cardDetailsBean.getRealName());
        bean.setQq(cardDetailsBean.getQq());
        bean.setMobile(cardDetailsBean.getMobile());
        bean.setPhone(cardDetailsBean.getPhone());
        bean.setImagePhoto(cardDetailsBean.getImagePhoto());
        bean.setIndustry(cardDetailsBean.getIndustry());
        bean.setAddressInfo(cardDetailsBean.getAddressInfo());
        createBean.setValue(bean);
    }

    public void getRepresentative(String cardId) {
        repository.getRepresentativeInfo(cardId,representativeBean);
    }
}
