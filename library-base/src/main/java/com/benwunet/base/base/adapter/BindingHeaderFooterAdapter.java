package com.benwunet.base.base.adapter;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

import java.util.List;


/**
 * DataBinding 基类适配器
 *
 * @author gavin.xiong 2016/12/28
 */
public class BindingHeaderFooterAdapter<T> extends RecyclerHeaderFooterAdapter<T, ViewDataBinding> {

    private IntConsumer onItemClickListener;

    public BindingHeaderFooterAdapter(Context context, List<T> list, @LayoutRes int layoutId) {
        super(context, list, layoutId);
    }

    public void setOnItemClickListener(IntConsumer listener) {
        this.onItemClickListener = listener;
    }

    @Override
    protected void onBind(RecyclerHolder<ViewDataBinding> holder, final int position, T t) {
//        holder.binding.setVariable(BR.item, t);
        holder.binding.executePendingBindings();
//        if (onItemClickListener != null) {
//            holder.itemView.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.accept(position);
//                }
//            });
//        }
    }

}
