package com.benwunet.user.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

import com.benwunet.base.base.adapter.IntConsumer;
import com.benwunet.base.base.adapter.RecyclerHeaderFooterAdapter;
import com.benwunet.base.base.adapter.RecyclerHolder;
import com.benwunet.user.BR;
import com.benwunet.user.R;

import java.util.List;

/**
 * @Package: com.benwunet.user.ui.activity
 * @ClassName: BindingHeaderAdapter
 * @Description: java类作用描述
 * @Author: feng
 * @CreateDate: 2020/11/17 0017 10:22
 * @Version: 1.0
 */


public class BindingHeaderAdapter<T> extends RecyclerHeaderFooterAdapter<T, ViewDataBinding>  {
    private IntConsumer onItemClickListener;

    public BindingHeaderAdapter(Context context, List<T> list, @LayoutRes int layoutId) {
        super(context, list, layoutId);
    }

    public void setOnItemClickListener(IntConsumer listener) {
        this.onItemClickListener = listener;
    }


    @Override
    protected void onBind(RecyclerHolder<ViewDataBinding> holder, final int position, T t) {
        holder.binding.setVariable(BR.item, t);
        holder.binding.executePendingBindings();
        if (onItemClickListener != null) {
            holder.itemView.findViewById(R.id.iv_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.accept(position);
                }
            });
        }

    }
}
