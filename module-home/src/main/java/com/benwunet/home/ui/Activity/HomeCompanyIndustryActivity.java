package com.benwunet.home.ui.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.BaseActivity;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.view.ClearEditText;
import com.benwunet.home.BR;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ActivityHomeCompanyIndustryBinding;
import com.benwunet.home.ui.adapter.CompanyIndustryAdapter;
import com.benwunet.home.ui.viewmodel.CompanyIndustryViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Package: com.benwunet.home.ui.activity
 * @ClassName: HomeCompanyIndustryActivity
 * @Description: 选择行业
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */
public class HomeCompanyIndustryActivity extends BaseActivity<ActivityHomeCompanyIndustryBinding, CompanyIndustryViewModel> {

    private CompanyIndustryAdapter adapter;
    private Context mContext;

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
        mContext=this;
        View view = LayoutInflater.from(this).inflate(R.layout.view_search, null);
        ClearEditText cet = view.findViewById(R.id.search_cet);
        binding.ntb.addCenterLayout(view);
        binding.ntb.setRightTitle("取消");
        binding.ntb.setTvRightTextColor(getColor(R.color.white));
        binding.ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cet.setOnEnterClickListener(new ClearEditText.OnClearEditTextListener() {
            @Override
            public void onAction(EditText et, String etString) {
                ToastUtils.showShort("OnEnterClick:" + etString);
            }
        });

        cet.setOnCancelClickListener(new ClearEditText.OnClearEditTextListener() {
            @Override
            public void onAction(EditText et, String etString) {
                ToastUtils.showShort("OnCancelClick:" + etString);
            }
        });

        adapter = new CompanyIndustryAdapter(R.layout.item_company_industry_view, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtils.showShort("pos=" + position);
                viewModel.singleCheckPosition(viewModel.listData.getValue(), position);
                adapter.notifyDataSetChanged();
            }
        });
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));


        binding.recyclerview.setAdapter(adapter);
//        adapter.addChildClickViewIds(R.id.iv_delete);

        binding.recyclerview.setAdapter(adapter);

        viewModel.listData.observe(this, new Observer<List<BaseCustomViewModel>>() {
            @Override
            public void onChanged(List<BaseCustomViewModel> baseCustomViewModels) {
                adapter.setNewData(viewModel.listData.getValue());
                adapter.notifyDataSetChanged();
            }
        });


        viewModel.finish.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Intent intent = new Intent();
                    intent.putExtra("industry", viewModel.getProfession());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

    }


}
