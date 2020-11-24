package com.benwunet.user.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserPeopleBinding;
import com.benwunet.user.ui.adapter.TopicAdapter;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.SelectDialog;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserSeePeopleActivity
 * @Description: java类作用描述
 * @Author: feng
 * @CreateDate: 2020/11/17 0017 16:51
 * @Version: 1.0
 */


public class UserSeePeopleActivity extends BaseActivity<ActivityUserPeopleBinding, BaseViewModel>  {
    private Context mContext;
    private TopicAdapter adapter;
    private PromptDialog promptDialog;
    private String title;

    //拿到路由过来的参数

    @Override
    public void initParam() {
         title = getIntent().getStringExtra("title");
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_people;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mContext = this;
        promptDialog = new PromptDialog(this);
        adapter = new TopicAdapter(R.layout.more_item_people_view,mContext);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        if(title!=null){
            binding.ntb.setNewTitleText(getString(R.string.user_no_let));
        }
        List<BaseCustomViewModel> data=new ArrayList<>();
        ThemesItemViewModel themesItemViewModel1 = new ThemesItemViewModel();
        themesItemViewModel1.title="杨幂";
        themesItemViewModel1.coverUrl="http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg";
        ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
        themesItemViewModel.title="张赞";
        themesItemViewModel.coverUrl="http://img.kaiyanapp.com/82c2122f6773c0d298eeb0112948cae2.jpeg?imageMogr2/quality/60/format/jpg";
        data.add(themesItemViewModel1);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel1);
        adapter.addChildClickViewIds(R.id.iv_delete);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull final BaseQuickAdapter adapter, @NonNull View view, final int position) {
                SelectDialog.build(mContext, "确定移除此人吗？", "", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(position);

                    }
                },"取消",null).setDialogStyle(DialogSettings.STYLE_KONGZUE).showDialog();
            }
        });
        adapter.setNewData(data);
        binding.recyclerview.setAdapter(adapter);
    }

    @Override
    public void initViewObservable() {
    }
}
