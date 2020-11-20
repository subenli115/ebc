package com.benwunet.user.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserFollowBinding;
import com.benwunet.user.ui.adapter.UserFollowAdapter;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserAboutActivity
 * @Description: 我的关注
 * @Author: feng
 * @CreateDate: 2020/11/12 0012 14:58
 * @Version: 1.0
 */


public class UserFollowActivity extends BaseActivity<ActivityUserFollowBinding, BaseViewModel> {
    private Context mContext;
    private UserFollowAdapter adapter;
    private ArrayList<ThemesItemViewModel> data;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_follow;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        data = new ArrayList<>();
        ThemesItemViewModel themesItemViewModel1 = new ThemesItemViewModel();
        themesItemViewModel1.title = "杨幂";
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
        themesItemViewModel.title = "张赞";
        themesItemViewModel.coverUrl = "http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        ThemesItemViewModel themesItemViewModel2 = new ThemesItemViewModel();
        themesItemViewModel1.title = "杨幂";
        themesItemViewModel1.coverUrl = "http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel2);
        initView();
    }

    private void initView() {
        binding.ntb.setRightTitle("取消关注");
        binding.ntb.setTvRightTextColor(Color.WHITE);
        binding.ntb.getRightTextView().setVisibility(View.GONE);
        binding.ntb.setOnRightTextListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                binding.ntb.getIvRight().setVisibility(View.VISIBLE);
                binding.ntb.getRightTextView().setVisibility(View.GONE);
                for(int i=data.size()-1;i>=0;i--){
                    ThemesItemViewModel themesItemViewModel = data.get(i);
                    if(themesItemViewModel.isSelect){
                       adapter.remove(i);
                    }else {
                        themesItemViewModel.isVisible=false;
                        adapter.setData(i,themesItemViewModel);
                    }
                }

            }
        });
        adapter = new UserFollowAdapter(R.layout.item_user_follow_view, mContext);
        binding.ntb.setOnRightImagListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                binding.ntb.getIvRight().setVisibility(View.GONE);
                binding.ntb.getRightTextView().setVisibility(View.VISIBLE);
                for(int i=0;i<data.size();i++){
                    ThemesItemViewModel themesItemViewModel = data.get(i);
                    themesItemViewModel.isVisible=true;
                    themesItemViewModel.isSelect=false;
                    adapter.setData(i,themesItemViewModel);
                }
            }
        });
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        adapter.addChildClickViewIds(R.id.iv_select);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                ThemesItemViewModel bean = (ThemesItemViewModel) adapter.getData().get(position);
                bean.isSelect = !bean.isSelect;
                adapter.setData(position, bean);

            }
        });
        adapter.addData(data);
        binding.recyclerview.setAdapter(adapter);
    }

}
