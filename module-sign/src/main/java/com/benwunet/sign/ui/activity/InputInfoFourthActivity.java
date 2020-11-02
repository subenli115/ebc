package com.benwunet.sign.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.sign.BR;
import com.benwunet.sign.R;
import com.benwunet.sign.databinding.ActivityInputInfoFourthBinding;
import com.benwunet.sign.ui.bean.CompleteInfoBean;
import com.benwunet.sign.ui.bean.TopicBean;
import com.benwunet.sign.ui.viewmodel.InfoViewModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.sign.ui.activity
 * @ClassName: InputInfofourthActivity
 * @Description: 完善资料页面4
 * @Author: feng
 * @CreateDate: 2020/10/28 0028 15:05
 * @Version: 1.0
 */


public class InputInfoFourthActivity extends BaseActivity<ActivityInputInfoFourthBinding, InfoViewModel> {

    private CompleteInfoBean entity;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_input_info_fourth;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
    }


    @Override
    public void initParam() {
        //获取列表传入的实体
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            entity = mBundle.getParcelable("entity");
        }
    }



    @Override
    public void initData() {
        binding.setLifecycleOwner(this);
        viewModel.initTagData();
        viewModel.setInfoEntity(entity);
        viewModel.hotTag.observe(this, new Observer<TopicBean>() {
            @Override
            public void onChanged(TopicBean topicBean) {
                TagAdapter mAdapter=  new TagAdapter<TopicBean.ListBean>(topicBean.getList()) {
                    @Override
                    public View getView(FlowLayout parent, int position, TopicBean.ListBean listBean)
                    {
                        final LayoutInflater mInflater = LayoutInflater.from(InputInfoFourthActivity.this);
                        TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_bg_other,
                                parent, false);
                        tv.setText(listBean.getTopicTitle());
                        return tv;
                    }

                };
                binding.tflHot.setAdapter(mAdapter);
            }
        });

        viewModel.newTag.observe(this, new Observer<TopicBean>() {
            @Override
            public void onChanged(TopicBean topicBean) {
                TagAdapter mAdapter=  new TagAdapter<TopicBean.ListBean>(topicBean.getList()) {
                    @Override
                    public View getView(FlowLayout parent, int position, TopicBean.ListBean listBean)
                    {
                        final LayoutInflater mInflater = LayoutInflater.from(InputInfoFourthActivity.this);
                        TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_bg_other,
                                parent, false);
                        tv.setText(listBean.getTopicTitle());
                        return tv;
                    }

                };
                binding.tflNew.setAdapter(mAdapter);
            }
        });
        viewModel.complete.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
            }
        });
    }

}
