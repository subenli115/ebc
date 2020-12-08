package com.benwunet.cards.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.cards.databinding.ItemCardsPagerBinding;
import com.benwunet.cards.ui.viewmodel.DynamicItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>     动态
 *
 * @author feng
 * @since 2020-02-23
 */
public class CardsPagerAdapter
    extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder>
{
    private final Context context;

    public CardsPagerAdapter(int layoutResId, Context mContext)
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
        DynamicItemViewModel item = (DynamicItemViewModel) baseCustomViewModel;
        ItemCardsPagerBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            binding.setViewModel((DynamicItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
               binding.rlAdd.setVisibility(item.isVisible ? View.VISIBLE : View.GONE);
               binding.ivCard.setVisibility(item.isVisible ? View.GONE : View.VISIBLE);
               binding.ivCard.setImageBitmap(BitmapFactory.decodeFile(item.coverUrl));
        }
    }
}