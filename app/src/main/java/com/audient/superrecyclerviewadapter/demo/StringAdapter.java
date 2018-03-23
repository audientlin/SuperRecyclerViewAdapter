package com.audient.superrecyclerviewadapter.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.audient.super_recycler_view_adapter.SuperRecyclerViewAdapter;
import com.audient.super_recycler_view_adapter.SuperViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

// 这里Adapter的实体是什么就传入什么，这里传入String
public class StringAdapter extends SuperRecyclerViewAdapter<String> {
    public StringAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, String datum) {
        ViewHolder holder = (ViewHolder) viewHolder;

        holder.mTvText.setText(datum);
    }

    // 返回ITEM的布局文件
    @Override
    public int getLayoutResId() {
        return R.layout.item_string;
    }

    static class ViewHolder extends SuperViewHolder {
        @BindView(R.id.tv_text) TextView mTvText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
