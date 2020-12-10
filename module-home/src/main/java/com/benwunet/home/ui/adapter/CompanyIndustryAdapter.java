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
 * <p>   我的关注
 *
 * @author feng
 * @since 2020-02-23
 */
public class CompanyIndustryAdapter extends BaseQuickAdapter<IndustryListBean, BaseViewHolder> {
    public List<String> selectList = new ArrayList<>();
    public CompanyIndustryAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
    }
    public void setSelectList(List<String> list){
        selectList.addAll(list);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull final BaseViewHolder baseViewHolder, @Nullable final IndustryListBean baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        ItemCompanyIndustryViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel( baseCustomViewModel);
            binding.executePendingBindings();
            if(baseCustomViewModel.isSelect()){
                binding.check.setBackgroundResource(R.mipmap.ic_checked);
            }else {
                binding.check.setBackgroundResource(R.mipmap.ic_unchecked);
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)binding.llItem.getLayoutParams();
            if(baseCustomViewModel.isVisit()){
                layoutParams.height= (int) CommonUtils.dp2px(getContext(),44);
            }else {
                layoutParams.height=0;
            }


            binding.check.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(selectList.size()==3&&!baseCustomViewModel.isSelect()){
                        ToastUtils.showLong("最多选择3项！");
                    }else {
                        singleCheckPosition(baseViewHolder.getLayoutPosition());
                    }
                }
            });
        }
    }

    public void singleCheckPosition(int pos) {
        IndustryListBean bean = getData().get(pos);
        if(selectList.size()<3&&!bean.isSelect()){
            bean.setSelect(true);
            selectList.add(bean.getIndustryName());
        }else if(bean.isSelect()){
            bean.setSelect(false);
            selectList.remove(bean.getIndustryName());
        }
        notifyItemChanged(pos);
    }
}