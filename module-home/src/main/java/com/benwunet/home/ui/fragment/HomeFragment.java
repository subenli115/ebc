package com.benwunet.home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.benwunet.base.base.fragment.BaseFragment;
import com.benwunet.base.contract.AppConstans;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.base.wdiget.SharePopupWindow;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.FragmentHome1Binding;
import com.benwunet.home.databinding.FragmentHomeBinding;
import com.benwunet.home.ui.Activity.HomeCodeActivity;
import com.benwunet.home.ui.Activity.HomeCreateCardActivity;
import com.benwunet.home.ui.Activity.HomeGroupListActivity;
import com.benwunet.home.ui.bean.CardDetailsBean;
import com.benwunet.home.ui.bean.RepresentativeBean;
import com.benwunet.home.ui.viewmodel.HomeViewModel;

import me.jessyan.autosize.internal.CustomAdapt;

import static android.app.Activity.RESULT_OK;

/**
 * @Package: com.benwunet.home.ui.fragment
 * @ClassName: HomeFragment
 * @Description: 我的名片
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<FragmentHome1Binding, HomeViewModel> {

    private static final int CREATE_CODE = 0;
    private String realName;
    private boolean isRepresentative;
    private String cardId;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.tvCreate.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(getContext(), HomeCreateCardActivity.class);
                startActivityForResult(intent, CREATE_CODE);
            }
        });
        binding.llEdit.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(getContext(), HomeCreateCardActivity.class);
                intent.putExtra("type", "edit");
                startActivityForResult(intent, CREATE_CODE);
            }
        });
        binding.llSend.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                final SharePopupWindow popupWindow = new SharePopupWindow(getContext());
                popupWindow.getRlCode().setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        /**
                         * 是商务代表并且是主企业
                         */
                        CardDetailsBean.CompanyListBean bean = viewModel.companyListBean.getValue();
                        if(bean!=null&&bean.isIsMainCompany()&&isRepresentative){
                            HomeCodeActivity.startAction(realName, cardId, true,bean.getCompanyName());
                        }else {
                            HomeCodeActivity.startAction(realName, cardId, false,"");
                        }
                        popupWindow.dismiss();
                    }
                });
                popupWindow.getRlGroup().setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        startActivity(HomeGroupListActivity.class);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setCopyText(AppConstans.cardDetailURL+cardId);
                popupWindow.show();
            }
        });
        viewModel.getHomeData();
    }


    @Override
    public void initViewObservable() {
        viewModel.cardDetailsBeanMutableLiveData.observe(this, new Observer<CardDetailsBean>() {
            @Override
            public void onChanged(CardDetailsBean cardDetailsBean) {
                realName = cardDetailsBean.getRealName();
                cardId = cardDetailsBean.getCardId();
                viewModel.getRepresentative(cardId);
                showCard();
            }
        });
        viewModel.representativeBean.observe(this, new Observer<RepresentativeBean>() {
            @Override
            public void onChanged(RepresentativeBean representativeBean) {
                isRepresentative = representativeBean.isFlag();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            showCard();
            viewModel.getHomeData();
        }

    }

    private void showCard() {
        binding.tvCreate.setVisibility(View.GONE);
        binding.tvTitle.setVisibility(View.GONE);
        binding.tvHint.setVisibility(View.GONE);
        binding.llBottomContainer.setVisibility(View.VISIBLE);
        binding.llInfo.setVisibility(View.VISIBLE);
    }

}
