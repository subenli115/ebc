package com.benwunet.home.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.benwunet.base.utils.CommonUtils;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.home.R;
import com.benwunet.home.databinding.ItemCompanyIndustryViewBinding;
import com.benwunet.home.databinding.ItemHomeGroupViewBinding;
import com.benwunet.home.ui.bean.HomeGroupListBean;
import com.benwunet.home.ui.bean.IndustryListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>   我的群聊
 *
 * @author feng
 * @since 2020-02-23
 */
public class HomeGroupChatAdapter extends BaseQuickAdapter<HomeGroupListBean, BaseViewHolder> {
    public List<String> selectList = new ArrayList<>();

    public HomeGroupChatAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull final BaseViewHolder baseViewHolder, @Nullable final HomeGroupListBean baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        ItemHomeGroupViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel(baseCustomViewModel);
            binding.executePendingBindings();
            if (baseCustomViewModel.isSelect()) {
                binding.check.setBackgroundResource(R.mipmap.ic_checked);
            } else {
                binding.check.setBackgroundResource(R.mipmap.ic_unchecked);
            }

            binding.check.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    singleCheckPosition(baseViewHolder.getLayoutPosition());
                }
            });
        }
    }

    public void singleCheckPosition(int pos) {
        HomeGroupListBean bean = getData().get(pos);
        if (!bean.isSelect()) {
            bean.setSelect(true);
            selectList.add(bean.getGroupName());
        } else if (bean.isSelect()) {
            bean.setSelect(false);
            selectList.remove(bean.getGroupName());
        }
        notifyItemChanged(pos);
    }
}