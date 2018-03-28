package com.audient.super_recycler_view_adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：
 * <p>
 * 注意事项：<br/>
 * </p>
 * <p>
 * 由 591928179@qq.com 创建于 2018/3/28.<br/>
 * 由 591928179@qq.com 修改于 2018/3/28.
 * </p>
 */
public abstract class SuperRecyclerViewAdapter2<M, BINDING extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected List<M> mData = new ArrayList<>();

    public SuperRecyclerViewAdapter2(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutResId(), parent, false);
        return new SuperViewHolder<BINDING>(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        SuperViewHolder holder = (SuperViewHolder) viewHolder;
        BINDING binding = (BINDING) holder.mBinding;
        final M datum = mData.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(v, position, datum);
            }
        });

        // if mOnItemLongClickListener == null, return false
        // else return mOnItemLongClickListener.onItemLongClick()
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnItemLongClickListener != null && mOnItemLongClickListener.onItemLongClick(v, position, datum);
            }
        });

        onBindViewHolder(binding, position, datum);
    }

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void onBindViewHolder(BINDING binding, int position, M datum);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public boolean add(M datum) {
        return mData.add(datum);
    }

    public void add(int index, M datum) {
        mData.add(index, datum);
    }

    public boolean addAll(Collection<? extends M> data) {
        return mData.addAll(data);
    }

    public boolean addAll(int index, Collection<? extends M> data) {
        return mData.addAll(index, data);
    }

    public void setData(List<M> data) {
        if (data != null) {
            mData = data;
        }
    }

    public List<M> getData() {
        return mData;
    }

    public boolean remove(M datum) {
        return mData.remove(datum);
    }

    public void remove(int position) {
        mData.remove(position);
    }

    public void clear() {
        mData.clear();
    }

    static class SuperViewHolder<BINDING extends ViewDataBinding> extends RecyclerView.ViewHolder {

        BINDING mBinding;

        SuperViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    /////////// START: Listeners ///////////

    protected SuperRecyclerViewAdapter.OnItemClickListener<M> mOnItemClickListener;
    protected SuperRecyclerViewAdapter.OnItemLongClickListener<M> mOnItemLongClickListener;

    public void setOnItemClickListener(SuperRecyclerViewAdapter.OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(SuperRecyclerViewAdapter.OnItemLongClickListener<M> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @FunctionalInterface
    public interface OnItemClickListener<M> {
        /**
         * @param datum 当前点击的ITEM对应的数据实体
         */
        void onItemClick(View view, int position, M datum);
    }

    @FunctionalInterface
    public interface OnItemLongClickListener<M> {
        /**
         * @param datum 当前点击的ITEM对应的数据实体
         * @return true if the callback consumed the long click, false otherwise
         */
        boolean onItemLongClick(View view, int position, M datum);
    }

    /////////// END: Listeners /////////////
}
