package com.benwunet.find.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.viewbinding.ViewBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.activity.CommHttpActivity;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.databinding.ActivityBaseCommonBinding;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.find.BR;
import com.benwunet.find.R;
import com.benwunet.find.databinding.ItemFindTopicHeadViewBinding;
import com.benwunet.find.ui.adapter.FindTopicHomeAdapter;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.benwunet.find.ui.viewmodel.FindViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @Package: com.benwunet.find.ui.activity
 * @ClassName: FindTopicHomeActivity
 * @Description: 话题主页
 * @Author: feng
 * @CreateDate: 2020/10/20 0020 11:14
 * @Version: 1.0
 */
public class FindTopicHomeActivity extends CommHttpActivity<ActivityBaseCommonBinding, FindViewModel> {
    private Context mContext;
    private String url = "http://zrwlmeiliao.oss-accelerate.aliyuncs.com/banner/xxx.png";
    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }


    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mContext = this;
        baseBinding.ntb.setRightImag2Src(R.mipmap.icon_bar_more);
        baseBinding.ntb.setRightImagSrc(R.mipmap.ntb_search);
        baseBinding.ntb.setOnRightImag2Listener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                View mMenuView = getLayoutInflater().inflate(R.layout.pop_find_topic_me, null);
                final PopupWindow pop =new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                mMenuView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        startActivity(FindTopicMyListActivity.class);
                        pop.dismiss();
                    }
                });
                pop.showAsDropDown(v);
            }
        });
        baseBinding.ntb.setNewTitleText(getString(R.string.base_topic));
        final List<BaseCustomViewModel> data = new ArrayList<>();
        DynamicItemViewModel themesItemViewModel = new DynamicItemViewModel();
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        data.add(themesItemViewModel);
        updateData(data);
    }

    @Override
    public void initHeadView() {
        setBinnerData((ItemFindTopicHeadViewBinding) headBinding);
    }

    @Override
    protected void loadData() {
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new FindTopicHomeAdapter(R.layout.item_find_home_topic, this);
    }

    @Override
    public ViewBinding getHeader() {
        return ItemFindTopicHeadViewBinding.inflate(LayoutInflater.from(this));
    }


    public void setBinnerData(ItemFindTopicHeadViewBinding binding) {
        binding.banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                RequestOptions roundOptions = new RequestOptions()
                        .transform(new RoundedCorners(10));
                Glide.with(mContext)
                        .load(model)
                        .apply(roundOptions)
                        .into(itemView);
            }
        });
        binding.tvFocus.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindFocusTopicActivity.class);
            }
        });
        binding.tvTopicList.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(FindTopicListActivity.class);
            }
        });
        binding.banner.setData(Arrays.asList(url, url, url), null);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}