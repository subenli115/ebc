package com.benwunet.find.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.find.databinding.ItemFindRecommendBinding;
import com.benwunet.find.ui.viewmodel.DynamicItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>      发现推荐
 *
 * @author feng
 * @since 2020-02-23
 */
public class FindRecommendAdapter
        extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {
    private final Context context;
    private String url = "http://zrwlmeiliao.oss-accelerate.aliyuncs.com/banner/xxx.png";

    public FindRecommendAdapter(int layoutResId, Context mContext) {
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
        ItemFindRecommendBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel((DynamicItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
            ArrayList<String> list = new ArrayList<>();
            list.add(url);
            list.add(url);
            list.add(url);
            binding.nplItemMomentPhotos.setData(list);
        }

    }
}