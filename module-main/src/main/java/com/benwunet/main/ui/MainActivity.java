package com.benwunet.main.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.benwunet.base.router.RouterActivityPath;
import com.benwunet.base.router.RouterFragmentPath;
import com.benwunet.base.view.ScrollControlViewPager;
import com.benwunet.base.wdiget.NormalTitleBar;
import com.benwunet.main.R;
import com.benwunet.main.adapter.ViewPagerAdapter;
import com.benwunet.msg.DemoHelper;
import com.benwunet.msg.common.constant.DemoConstant;
import com.benwunet.msg.common.manager.HMSPushHelper;
import com.benwunet.msg.common.permission.PermissionsManager;
import com.benwunet.msg.common.permission.PermissionsResultAction;
import com.benwunet.msg.section.MainViewModel;
import com.benwunet.msg.section.base.BaseInitActivity;
import com.benwunet.msg.section.chat.ChatPresenter;
import com.benwunet.msg.section.contact.viewmodels.ContactsViewModel;
import com.benwunet.msg.section.conversation.ConversationListFragment;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.ui.base.EaseBaseFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseInitActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final int CARD_ITEM = 0;
    private static final int INFO_ITEM = 1;
    private static final int MSG_ITEM = 2;
    private static final int ME_ITEM = 3;
    private static final int FIND_ITEM = 4;
    private List<Fragment> mFragments;
    private EaseBaseFragment mConversationListFragment;
    private EaseBaseFragment mCurrentFragment;
    private MainViewModel viewModel;
    private NormalTitleBar ntb;
    private int[] mBtnListID;
    private Button[] mBtnList;
    private TextView mTvMainHomeMsg;
    private ImageView findBtn;
    private ScrollControlViewPager mViewContainer;

    public static void startAction(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.msg_activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.benwunet.msg.R.menu.demo_conversation_menu, menu);
        return true;
    }

    @Override
    protected void initSystemFit() {
        setFitSystemForTheme(true,R.color.transparent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mViewContainer =  findViewById(com.benwunet.msg.R.id.viewpager);
        ntb = findViewById(com.benwunet.msg.R.id.ntb);
        mTvMainHomeMsg = findViewById(com.benwunet.msg.R.id.all_unread_number);
        initFragment();
        mBtnListID = new int[]{
                com.benwunet.msg.R.id.actionbar_info_btn, com.benwunet.msg.R.id.actionbar_card_btn,
                com.benwunet.msg.R.id.actionbar_msg_btn, com.benwunet.msg.R.id.actionbar_me_btn};
        mBtnList = new Button[mBtnListID.length];
        for (int i = 0; i < mBtnListID.length; i++) {
            mBtnList[i] =  findViewById(mBtnListID[i]);
        }
        mBtnList[0].setTextColor(getResources().getColor(R.color.actionbar_pres_color));
        mBtnList[0].setSelected(true);
        ntb.setTitleText("我的名片");
        findBtn =  findViewById(com.benwunet.msg.R.id.actionbar_find_btn);
        setOnClickListener(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected void initData() {
        super.initData();
        initViewModel();
        requestPermissions();
        checkUnreadMsg();
        ChatPresenter.getInstance().init();
        // 获取华为 HMS 推送 token
        HMSPushHelper.getInstance().getHMSToken(this);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(mContext).get(MainViewModel.class);
        viewModel.getSwitchObservable().observe(this, response -> {
            if (response == null || response == 0) {
                return;
            }
        });

        viewModel.homeUnReadObservable().observe(this, readCount -> {
            if (!TextUtils.isEmpty(readCount)) {
                mTvMainHomeMsg.setVisibility(View.VISIBLE);
                mTvMainHomeMsg.setText(readCount);
            } else {
                mTvMainHomeMsg.setVisibility(View.GONE);
            }
        });
        //加载联系人
        ContactsViewModel contactsViewModel = ViewModelProviders.of(mContext).get(ContactsViewModel.class);
        contactsViewModel.loadContactList();

        viewModel.messageChangeObservable().with(DemoConstant.GROUP_CHANGE, EaseEvent.class).observe(this, this::checkUnReadMsg);
        viewModel.messageChangeObservable().with(DemoConstant.NOTIFY_CHANGE, EaseEvent.class).observe(this, this::checkUnReadMsg);
        viewModel.messageChangeObservable().with(DemoConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.class).observe(this, this::checkUnReadMsg);

        viewModel.messageChangeObservable().with(DemoConstant.CONVERSATION_DELETE, EaseEvent.class).observe(this, this::checkUnReadMsg);
        viewModel.messageChangeObservable().with(DemoConstant.CONTACT_CHANGE, EaseEvent.class).observe(this, this::checkUnReadMsg);

    }

    private void checkUnReadMsg(EaseEvent event) {
        if (event == null) {
            return;
        }
        viewModel.checkUnreadMsg();
    }


//    /**
//     * 用于展示是否已经存在的Fragment
//     *
//     * @param savedInstanceState
//     */
//    private void checkIfShowSavedFragment(Bundle savedInstanceState) {
//        if (savedInstanceState != null) {
//            String tag = savedInstanceState.getString("tag");
//            if (!TextUtils.isEmpty(tag)) {
//                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
//                if (fragment instanceof EaseBaseFragment) {
//                    replace((EaseBaseFragment) fragment, tag);
//                }
//            }
//        }
//    }

    /**
     * 申请权限
     */
    // TODO: 2019/12/19 0019 有必要修改一下
    private void requestPermissions() {
        PermissionsManager.getInstance()
                .requestAllManifestPermissionsIfNecessary(mContext, new PermissionsResultAction() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(String permission) {

                    }
                });
    }


//    private void replace(EaseBaseFragment fragment, String tag) {
//        if (mCurrentFragment != fragment) {
//            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
//            if (mCurrentFragment != null) {
//                t.hide(mCurrentFragment);
//            }
//            mCurrentFragment = fragment;
//            if (!fragment.isAdded()) {
//                t.add(R.id.fl_main_fragment, fragment, tag).show(fragment).commit();
//            } else {
//                t.show(fragment).commit();
//            }
//
//        }
//    }


    public void setButtonColor(int index) {
        for (int i = 0; i < mBtnListID.length; i++) {
            if (index != 4 && index == i) {
                mBtnList[i].setSelected(true);
                mBtnList[i].setTextColor(getResources().getColor(com.benwunet.msg.R.color.actionbar_pres_color));
            } else {
                mBtnList[i].setSelected(false);
                mBtnList[i].setTextColor(getResources().getColor(com.benwunet.msg.R.color.action_bar_txt_color));
            }
        }
    }


    private void checkUnreadMsg() {
        viewModel.checkUnreadMsg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DemoHelper.getInstance().showNotificationPermissionDialog();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrentFragment != null) {
            outState.putString("tag", mCurrentFragment.getTag());
        }
    }


    private void initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        Fragment cardFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Card.PAGER_CARD).navigation();
        mConversationListFragment = new ConversationListFragment();
        Fragment findFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Find.PAGER_FIND).navigation();
        Fragment meFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.User.PAGER_ME).navigation();
        mFragments = new ArrayList<>();
        mFragments.add(homeFragment);
        mFragments.add(cardFragment);
        mFragments.add(mConversationListFragment);
        mFragments.add(meFragment);
        mFragments.add(findFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(mContext.getSupportFragmentManager(),
                mFragments);
        mViewContainer.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == com.benwunet.msg.R.id.actionbar_info_btn) {
            mViewContainer.setCurrentItem(CARD_ITEM, false);
        } else if (id == com.benwunet.msg.R.id.actionbar_card_btn) {
            mViewContainer.setCurrentItem(INFO_ITEM, false);
        } else if (id == com.benwunet.msg.R.id.actionbar_msg_btn) {
            mViewContainer.setCurrentItem(MSG_ITEM, false);
        } else if (id == com.benwunet.msg.R.id.actionbar_me_btn) {
            mViewContainer.setCurrentItem(ME_ITEM, false);
        } else if (id == com.benwunet.msg.R.id.actionbar_find_btn) {
            mViewContainer.setCurrentItem(FIND_ITEM, false);
        }

    }


    public void setOnClickListener(View.OnClickListener onclickListener) {
        for (int i = 0; i < mBtnListID.length; i++) {
            mBtnList[i].setOnClickListener(onclickListener);
        }
        findBtn.setOnClickListener(onclickListener);
        mViewContainer.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setButtonColor(position);
        ntb.setVisibility(View.VISIBLE);
        if (position == CARD_ITEM) {
            ntb.setTitleText(R.string.main_title_card);
        } else if (position == INFO_ITEM) {
            ntb.setTitleText(R.string.main_title_home);
        } else if (position == MSG_ITEM) {
            ntb.setTitleText(R.string.main_title_msg);
        } else if (position == ME_ITEM) {
            ntb.setVisibility(View.GONE);
        } else if (position == FIND_ITEM) {
            ntb.setTitleText(R.string.main_title_find);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
