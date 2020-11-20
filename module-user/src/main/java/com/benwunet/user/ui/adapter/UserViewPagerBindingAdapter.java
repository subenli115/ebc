package com.benwunet.user.ui.adapter;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.R;
import com.benwunet.user.databinding.UserItemViewpagerBinding;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.benwunet.user.ui.viewmodel.UserViewPagerItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

/**
 * Created by feng on 2018/6/21.
 * v
 */

public class UserViewPagerBindingAdapter extends BindingViewPagerAdapter<UserViewPagerItemViewModel> {

    private final Context mContext;
    private CollectionCardAdapter adapter;

    public UserViewPagerBindingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onBindBinding(final ViewDataBinding binding, int variableId, int layoutRes, final int position, UserViewPagerItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
        UserItemViewpagerBinding _binding = (UserItemViewpagerBinding) binding;
        _binding.recyclerview.setHasFixedSize(true);
        _binding.recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        if(position==0){
            CollectionCardAdapter    adapter = new CollectionCardAdapter(R.layout.item_user_collection_card, mContext);
            List<BaseCustomViewModel> data = new ArrayList<>();
            ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
            data.add(themesItemViewModel);
            data.add(themesItemViewModel);
            data.add(themesItemViewModel);
            adapter.setNewData(data);
            adapter.addChildClickViewIds(R.id.iv_more);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    PopupMenu menu = new PopupMenu(mContext, view);
                    menu.getMenuInflater().inflate(R.menu.bottom_main_nav_menu, menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.item_menu_collection) {
                                Toast.makeText(mContext, "menu", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(mContext, "1", Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }

                    });
                    menu.show();
                }
            });
            _binding.recyclerview.setAdapter(adapter);
        }else {
            _binding.tvNum.setVisibility(View.GONE);
            CollectionDynamicAdapter adapter = new CollectionDynamicAdapter(R.layout.item_user_collection_dynamic, mContext);
            List<BaseCustomViewModel> data = new ArrayList<>();
            ThemesItemViewModel themesItemViewModel = new ThemesItemViewModel();
            themesItemViewModel.title="想要完美的名片形象定制，还在犹豫什么？赶紧联系我！";
            data.add(themesItemViewModel);
            data.add(themesItemViewModel);
            data.add(themesItemViewModel);
            adapter.setNewData(data);
            adapter.addChildClickViewIds(R.id.iv_more);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    PopupMenu menu = new PopupMenu(mContext, view);
                    menu.getMenuInflater().inflate(R.menu.bottom_main_nav_menu, menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.item_menu_collection) {
                                Toast.makeText(mContext, "menu", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(mContext, "1", Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }

                    });
                    menu.show();
                }
            });
            _binding.recyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
