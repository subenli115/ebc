package com.benwunet.find.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.databinding.ItemFindHomeTopicBinding;
import com.benwunet.find.databinding.ItemFindTopicFocusBinding;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>     发现关注
 *
 * @author feng
 * @since 2020-02-23
 */
public class FindTopicFocusAdapter
    extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder>
{
    private final Context context;

    public FindTopicFocusAdapter(int layoutResId, Context mContext)
    {
        super(layoutResId);
        context=mContext;
    }
    
    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder,
        int viewType)
    {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder,
        @Nullable final BaseCustomViewModel baseCustomViewModel)
    {
        if (baseCustomViewModel == null)
        {
            return;
        }
        ItemFindTopicFocusBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            binding.setViewModel((DynamicItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}