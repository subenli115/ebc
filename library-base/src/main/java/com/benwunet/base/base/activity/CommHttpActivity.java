package com.benwunet.base.base.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.benwunet.base.R;
import com.benwunet.base.databinding.ActivityBaseCommonBinding;
import com.benwunet.base.model.BaseViewModel;
import com.benwunet.base.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;



public abstract class CommHttpActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<V, VM> implements OnRefreshLoadMoreListener , OnItemClickListener {
    /**
     * 默认每次加载返回条数为15条
     */
    private int mLoadCount = 15;
    /**
     * 基类数据源
     */
    public List mData;
    /**
     * 当前请求的页数
     */
    public int page = 1;
    private boolean refresh = true;
    public BaseQuickAdapter mAdapter;
    public ActivityBaseCommonBinding baseBinding;
    public ViewBinding headBinding;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_base_common;
    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    public void initData() {
        super.initData();
        baseBinding = (ActivityBaseCommonBinding) binding;
        loadData();
        initRecyclerView();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refresh = false;
        onRefreshBy(false);
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadData();
        refresh = true;
        refreshLayout.finishRefresh();
    }

    public void onRefreshBy(boolean refresh) {
        if (mAdapter != null) {
            if (mData != null && mData.size() > 0) {
                mData.clear();
                mAdapter.notifyDataSetChanged();
            }
        }
        page = 1;
        //发起请求
        if (refresh) {

        }
        //网络检查
        loadData();
    }

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 构建适配器
     *
     * @return 适配器
     */
    public abstract BaseQuickAdapter getAdapter();


    /**
     * 添加头部
     *
     * @return 适配器
     */
    public abstract ViewBinding getHeader();


    public void setHeader() {
        View root = headBinding.getRoot();
        mAdapter.addHeaderView(root);
    }

    /**
     * 初始化列表控件和适配器
     */
    protected void initRecyclerView() {
        baseBinding.refreshLayout.setOnRefreshLoadMoreListener(this);
        baseBinding.recyclerview.setHasFixedSize(true);
        baseBinding.recyclerview.setLayoutManager(getLayoutManager());
        mAdapter = getAdapter();
        if (baseBinding.recyclerview.getAdapter() == null) {
            baseBinding.recyclerview.setAdapter(mAdapter);

        }
        mAdapter.setOnItemClickListener(this);
        headBinding = getHeader();
        if(headBinding!=null){
            initHeadView();
            setHeader();
        }
    }

    public void initHeadView() {

    }

    ;

    /**
     * 获取RecyclerView的布局管理器
     *
     * @return RecyclerView的布局管理器
     */
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    /**
     * 更新数据
     *
     * @param data 数据源
     */
    public void updateData(List data) {
        if (baseBinding.recyclerview == null) {
            return;
        }
        if (EmptyUtils.isNotEmpty(data)) {
            page++;
            if (EmptyUtils.isEmpty(mData)) {
                mData = data;
                Log.e("updateData", "" + mData.size());
                mAdapter.addData(mData);
            }
            setRefresh(false);
            if (refresh) { //如果是下拉刷新则替换旧数据
                refresh = false;
                mAdapter.replaceData(data);
            } else {
                mAdapter.addData(data);
                if (data.size() < mLoadCount) {
                    baseBinding.refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        } else {    //数据为空
            if (getAdapter().getHeaderLayout() != null) {
                if (mAdapter.getItemCount() == 0) {
                    baseBinding.refreshLayout.finishLoadMoreWithNoMoreData();
                } else {

                }
            } else {
                if (mAdapter.getItemCount() > 0) {

                }
            }
            setRefresh(false);
        }
    }

    /**
     * 设置刷新状态
     *
     * @param refresh true 启动刷新  FALSE 刷新结束
     */
    public void setRefresh(boolean refresh) {
        if (baseBinding.recyclerview == null) {
            baseBinding.refreshLayout.setEnableRefresh(false);
        }
    }
}
