package com.audient.super_recycler_view_adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：一个超级好用的RecyclerViewAdapter
 * <p>
 * Created by audienl@qq.com on 2017/8/10.
 */
@Deprecated
public abstract class SuperRecyclerViewAdapter<M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected List<M> mData = new ArrayList<>();

    public SuperRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(LayoutInflater.from(mContext).inflate(getLayoutResId(), parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
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

        onBindViewHolder(holder, position, datum);
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(View view, int viewType);

    public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, M datum);

    @LayoutRes
    public abstract int getLayoutResId();

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

    /////////// START: Listeners ///////////

    protected OnItemClickListener<M> mOnItemClickListener;
    protected OnItemLongClickListener<M> mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<M> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @Deprecated
    @FunctionalInterface
    public interface OnItemClickListener<M> {
        /**
         * @param datum 当前点击的ITEM对应的数据实体
         */
        void onItemClick(View view, int position, M datum);
    }

    @Deprecated
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
