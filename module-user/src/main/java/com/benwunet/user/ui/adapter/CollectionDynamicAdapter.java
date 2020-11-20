package com.benwunet.user.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.databinding.ItemUserCollectionDynamicBinding;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>收藏的动态
 *
 * @author feng
 * @since 2020-02-23
 */
public class CollectionDynamicAdapter
    extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder>
{
    private final Context context;

    public CollectionDynamicAdapter(int layoutResId, Context mContext)
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
        ItemUserCollectionDynamicBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            binding.setViewModel((ThemesItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}