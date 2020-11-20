package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserManageBinding;
import com.benwunet.user.ui.adapter.UserBusinessManageAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.io.Serializable;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserBusinessActivity
 * @Description: 我的企业
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */


public class UserManageActivity extends BaseActivity<ActivityUserManageBinding, BaseViewModel> {

    private Context mContext;
    private UserBusinessManageAdapter adapter;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_manage;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        adapter = new UserBusinessManageAdapter(R.layout.item_user_manage_view,mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        List<BaseCustomViewModel> data = (List<BaseCustomViewModel>) getIntent().getSerializableExtra("data");
        adapter.setNewData(data);
        adapter.addChildClickViewIds(R.id.iv_delete);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        adapter.remove(position);

            }
        });
        binding.recyclerview.setAdapter(adapter);
        binding.tvSave.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("data", (Serializable) adapter.getData());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
