package com.benwunet.cards.ui.viewmodel;

import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.benwunet.base.model.BaseSingleLiveEvent;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.cards.R;
import com.benwunet.cards.BR;
import com.benwunet.cards.respository.CardsRepository;
import com.benwunet.cards.ui.bean.CardGroundItemBean;
import com.benwunet.cards.ui.bean.CardPaperBean;
import com.benwunet.cards.ui.bean.CardsEditBean;
import com.benwunet.cards.ui.bean.CardsHomeBean;
import com.benwunet.cards.ui.bean.CardsMindGroupListBean;
import com.benwunet.cards.ui.bean.CardsMineBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by feng on 2020/10/15.
 */

public class CardsViewModel extends BaseViewModel {
    private CardsRepository cardsRepository = CardsRepository.getInstance(this);
    public MutableLiveData<CardsHomeBean> cardsHomebean = new MutableLiveData<>();
    public MutableLiveData<List<CardsHomeBean.GroupsBean>> groups = new MutableLiveData<>();
    public MutableLiveData<List<CardPaperBean>> cardPaperList = new MutableLiveData<>();
    public MutableLiveData<List<CardsMindGroupListBean>> cardsMindGroupList = new MutableLiveData<>();
    public BaseSingleLiveEvent isDelete = new BaseSingleLiveEvent<>();
    public BaseSingleLiveEvent isAdd = new BaseSingleLiveEvent<>();
    public BaseSingleLiveEvent isEdit = new BaseSingleLiveEvent<>();
    public MutableLiveData<List<CardsMineBean>> cardsMineBeanList = new MutableLiveData<>();
    public MutableLiveData<List<CardGroundItemBean>> cardItemList = new MutableLiveData<>();

    public CardsViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 10; i++) {
            observableList.add(new CardsItemViewModel(this, "条目" + i));
        }
//        cardsRepository.getMemberCardPaper(cardPaperList);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<CardsItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CardsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.grid_work);


    public void initHomeData() {
        cardsRepository.getHomeData(cardsHomebean, groups);
    }

    /**
     *
     * 智能分组
     */
    public void initTypeGroupData(String type) {
        cardsRepository.getTypeGroupList(cardsMindGroupList, type);
    }

    /**
     *
     * 智能分组(名片列表)
     */
    public void getCardGroupList(List<String> cards) {
        cardsRepository.getCardGroupList(cardItemList, cards);
    }

    /**
     *
     * 我的分组
     */
    public void getCardMineGroupList() {
        cardsRepository.getCardMineList(cardsMineBeanList);
    }

    /**
     *
     * 删除分组
     */
    public void deleteCardMineGroup(String groupId) {
        cardsRepository.deleteCardsGroup(isDelete,groupId);
    }

    /**
     *
     * 新增分组
     */
    public void addGroup(String groupId) {
//        cardsRepository.addCardsGroup(isAdd,groupId);
    }
    /**
     *
     * 编辑分组
     */
    public void eidtGroup(String groupId) {
        CardsEditBean cardsEditBean = new CardsEditBean();

        cardsRepository.editCardsGroup(isEdit,cardsEditBean);
    }
}
