package com.audient.superrecyclerviewadapter.demo;

import android.content.Context;

import com.audient.super_recycler_view_adapter.SuperRecyclerViewAdapter2;
import com.audient.superrecyclerviewadapter.demo.databinding.ItemStringBinding;

/**
 * 这里Adapter的实体是什么就传入什么，这里传入String
 * ItemStringBinding 是根据xml文件名生成的
 */
public class StringAdapter extends SuperRecyclerViewAdapter2<String, ItemStringBinding> {
    public StringAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ItemStringBinding binding, int position, String datum) {
        binding.setText(datum);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_string;
    }
}
