package com.benwunet.user.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.benwunet.base.contract.BaseCustomViewModel;
import com.benwunet.base.wdiget.OnNoDoubleClickListener;
import com.benwunet.user.databinding.ItemUserManageViewBinding;
import com.benwunet.user.ui.viewmodel.ThemesItemViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>   企业管理
 *
 * @author feng
 * @since 2020-02-23
 */
public class UserBusinessManageAdapter
        extends BaseQuickAdapter<BaseCustomViewModel, BaseViewHolder> {
    private final Context context;

    public UserBusinessManageAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        context = mContext;
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder,
                                           int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull final BaseViewHolder baseViewHolder,
                           @Nullable final BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        ItemUserManageViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            ThemesItemViewModel item = (ThemesItemViewModel) baseCustomViewModel;
            binding.setViewModel((ThemesItemViewModel) baseCustomViewModel);
            binding.executePendingBindings();
            binding.ivMain.setSelected(item.isSelect);
            binding.ivMain.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    for(int i=0;i<getData().size();i++){
                        ThemesItemViewModel model = (ThemesItemViewModel) getData().get(i);
                        if(i!=baseViewHolder.getPosition()){
                            model.isSelect=false;
                            setData(i,model);
                        }else {
                            remove(baseViewHolder.getPosition());
                            model.isSelect=true;
                            addData(0,model);
                        }
                    }
                }
            });
        }
    }
}