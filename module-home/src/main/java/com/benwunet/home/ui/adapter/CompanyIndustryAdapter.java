package com.benwunet.home.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.home.databinding.ItemCompanyIndustryViewBinding;
import com.benwunet.home.ui.viewmodel.CompanyIndustryItemViewModel;
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
public class CompanyIndustryAdapter extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {

    public CompanyIndustryAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable final BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        ItemCompanyIndustryViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel( (CompanyIndustryItemViewModel)baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}