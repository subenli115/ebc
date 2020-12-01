package com.benwunet.user.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.base.adapter.IntConsumer;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.databinding.ItemUserFansViewBinding;
import com.benwunet.user.databinding.ItemUserFollowViewBinding;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
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
public class UserFansAdapter
        extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {
    private IntConsumer onItemClickListener;
    private final Context context;

    public UserFansAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        context = mContext;
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
        ItemUserFansViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            ThemesItemViewModel item = (ThemesItemViewModel) baseCustomViewModel;

            binding.setViewModel((ThemesItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
            binding.tvFollowNormal.setVisibility(item.isVisible ? View.VISIBLE : View.GONE);
            binding.tvFollow.setVisibility(item.isVisible ? View.GONE : View.VISIBLE);
        }
    }
}