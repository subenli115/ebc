package com.benwunet.user.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.databinding.ItemUserBillViewBinding;
import com.benwunet.user.ui.viewmodel.BillItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>   我的关注
 *
 * @author feng
 * @since 2020-02-23
 */
public class UserBillAdapter
        extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {

    public UserBillAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder,
                                           int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder,
                           @Nullable final BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        ItemUserBillViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            BillItemViewModel item = (BillItemViewModel) baseCustomViewModel;
            binding.setViewModel((BillItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}