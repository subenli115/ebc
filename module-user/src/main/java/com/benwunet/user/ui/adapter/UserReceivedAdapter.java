package com.benwunet.user.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.base.adapter.IntConsumer;
import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.user.databinding.ItemUserReceivedCardBinding;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>   我的企业
 *
 * @author feng
 * @since 2020-02-23
 */
public class UserReceivedAdapter
        extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {
    private IntConsumer onItemClickListener;
    private final Context context;
    ItemUserReceivedCardBinding binding;
    public UserReceivedAdapter(int layoutResId, Context mContext) {
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
         binding = baseViewHolder.getBinding();
        if (binding != null) {
            ThemesItemViewModel item = (ThemesItemViewModel) baseCustomViewModel;
            binding.setViewModel((ThemesItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
            setVisit(item.state.equals("1"));
        }
    }

    private void setVisit(boolean visible) {
        binding.tvRefuse.setVisibility(visible?View.VISIBLE:View.GONE);
        binding.tvChange.setVisibility(visible?View.VISIBLE:View.GONE);
        binding.tvState.setVisibility(visible?View.GONE:View.VISIBLE);
    }


}