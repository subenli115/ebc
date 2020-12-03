package com.benwunet.user.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.base.adapter.IntConsumer;
import com.benwunet.base.contract.AppConstans;
import com.benwunet.base.livedatas.LiveDataBus;
import com.benwunet.base.wdiget.BottomDialog;
import com.benwunet.base.wdiget.BottomMenuDialog;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.BR;
import com.benwunet.user.R;
import com.benwunet.user.databinding.ActivityUserHomeBinding;
import com.benwunet.user.databinding.ItemUserHomeHeadBinding;
import com.benwunet.user.ui.adapter.BindingHeaderAdapter;
import com.benwunet.user.ui.bean.Story;
import com.benwunet.user.ui.viewmodel.HomeViewModel;
import com.benwunet.user.ui.wdiget.MenuItemController;
import com.benwunet.user.ui.wdiget.MenuItemView;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: UserHomeActivity
 * @Description: 用户个人主页
 * @Author: feng
 * @CreateDate: 2020/11/16 0016 09:42
 * @Version: 1.0
 */


public class UserHomeActivity extends BaseActivity<ActivityUserHomeBinding, HomeViewModel> {
    private Context mContext;
    private BottomDialog mDialog;
    private View mMenuView;
    private PopupWindow mMenuPopWindow;
    private MenuItemView mMenuItemView;
    private MenuItemController mMenuController;
    private ItemUserHomeHeadBinding bb;
    private int viewModelId;
    private BottomMenuDialog mItemDialog;
    private ItemUserHomeHeadBinding headBinding;

    //拿到路由过来的参数

    @Override
    public void initParam() {
        //注入路由框架，拿到Autowired值，必须在initParam方法中注入，不然传到ViewModel里面的name为空
        ARouter.getInstance().inject(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        mContext = this;
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<Story> userList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Story u = new Story();
            u.setTitle("dfdfd");
            userList.add(u);
        }
        headBinding = ItemUserHomeHeadBinding.inflate(LayoutInflater.from(mContext));
        headBinding.ivTake.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPhotoDialog();
            }
        });
        headBinding.tvCard.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                LiveDataBus.get().with(AppConstans.BusTag.CLOSE).setValue("");
                finish();
            }
        });
        headBinding.ivEdit.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(UserInfoActivity.class);
            }
        });
        binding.ntb.setRightImag2Src(R.mipmap.icon_bar_more);
        binding.ntb.setOnRightImag2Listener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showPopWindow();
            }
        });
        BindingHeaderAdapter adapter = new BindingHeaderAdapter<>(this, userList, R.layout.item_user_dynamic_img);
        adapter.setHeaderBinding(headBinding);
        adapter.setOnItemClickListener(new IntConsumer() {
            @Override
            public void accept(int i) {
                showDialog(mContext);
            }
        });
        this.binding.recyclerview.setAdapter(adapter);
        mMenuView = getLayoutInflater().inflate(R.layout.drop_down_menu, null);
        mMenuPopWindow = new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        mMenuItemView = new MenuItemView(mMenuView);
        mMenuController = new MenuItemController(this);
        mMenuItemView.initModule();
        mMenuItemView.setListeners(mMenuController);
    }

    public void showPopWindow() {
        mMenuPopWindow.setTouchable(true);
        mMenuPopWindow.setOutsideTouchable(true);
        mMenuPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (mMenuPopWindow.isShowing()) {
            mMenuPopWindow.dismiss();
        } else {
            mMenuPopWindow.showAsDropDown(binding.ntb, 0, 0, Gravity.RIGHT);
        }
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.finishRefreshing.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                binding.twinklingRefreshLayout.finishRefreshing();
            }
        });
        //监听上拉加载完成
    }

    public void dismissPopWindow() {
        if (mMenuPopWindow.isShowing()) {
            mMenuPopWindow.dismiss();
        }
    }

    public void showPhotoDialog() {
        mDialog = new BottomDialog(this,headBinding.ivHead);
        mDialog.show();
    }

    public void showDialog(final Context context) {
        mItemDialog = new BottomMenuDialog(context, "删除", "置顶");
        mItemDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


            }

        });
        mItemDialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        });
        mItemDialog.show();
    }

}
