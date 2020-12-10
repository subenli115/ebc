package com.benwunet.home.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.base.adapter.SearchAdapter;
import com.benwunet.base.contract.AppConstans;
import com.benwunet.base.livedatas.LiveDataBus;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeCompanyIndustryBinding;
import com.benwunet.home.ui.adapter.CompanyIndustryAdapter;
import com.benwunet.home.ui.bean.IndustryListBean;
import com.benwunet.home.ui.viewmodel.CompanyIndustryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.benwunet.home.ui.activity
 * @ClassName: HomeCompanyIndustryActivity
 * @Description: 选择行业
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */
public class HomeCompanyIndustryActivity extends BaseActivity<ActivityHomeCompanyIndustryBinding, CompanyIndustryViewModel> implements TextWatcher {

    private CompanyIndustryAdapter adapter;
    private Context mContext;
    private String industry;
    private List<String> searchList;
    private int length;

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home_company_industry;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        if (getIntent() != null) {
            Intent intent = getIntent();
            industry = intent.getStringExtra("industry");
        }
        searchList = new ArrayList<>();
        View view = LayoutInflater.from(this).inflate(R.layout.view_search, null);
        final AutoCompleteTextView search = view.findViewById(R.id.search_cet);
        search.addTextChangedListener(this);
        binding.ntb.addCenterLayout(view);
        binding.ntb.setRightTitle("取消");
        binding.ntb.setTvRightTextColor(getColor(R.color.textColorYellow));
        binding.ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(length>0){

                }else {

                }
                search.setText("");
            }
        });
        adapter = new CompanyIndustryAdapter(R.layout.item_company_industry_view, mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerview.setAdapter(adapter);
        viewModel.industry.observe(this, new Observer<List<IndustryListBean>>() {
            @Override
            public void onChanged(List<IndustryListBean> industryListBeans) {
                for (int i = 0; i < industryListBeans.size(); i++) {
                    searchList.add(industryListBeans.get(i).getIndustryName());
                }
                SearchAdapter<String> searchAdapter = new SearchAdapter<String>(mContext,
                        android.R.layout.simple_list_item_1, searchList, 5);

                search.setAdapter(searchAdapter);
                adapter.setNewData(viewModel.initListData(industry, industryListBeans, adapter));
            }
        });

    }


    @Override
    public void initViewObservable() {
        viewModel.finish.observe(this, new Observer() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(Object o) {
                LiveDataBus.get().with(AppConstans.BusTag.UPDATE).setValue(viewModel.getProfession(adapter.selectList));
                finish();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
         length = s.toString().length();
        if(length>0){
            binding.ntb.getRightTextView().setText("搜索");
        }else {
            binding.ntb.getRightTextView().setText("取消");
        }
//        if (s.toString().length() > 0) {
//            for (int i = 0; i < searchList.size(); i++) {
//                if (searchList.get(i).equals(s.toString())) {
//                    adapter.getData().get(i).setVisit(true);
//                } else {
//                    adapter.getData().get(i).setVisit(false);
//                }
//            }
//        } else {
//            for (int i = 0; i < searchList.size(); i++) {
//                adapter.getData().get(i).setVisit(true);
//            }
//        }
//        adapter.notifyDataSetChanged();
    }
}
