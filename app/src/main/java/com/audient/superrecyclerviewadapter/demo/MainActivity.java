package com.audient.superrecyclerviewadapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private StringAdapter mStringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStringAdapter = new StringAdapter(this);
        mRecyclerView.setAdapter(mStringAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置数据
        List<String> texts = Arrays.asList("毛泽东", "邓小平", "江泽民", "胡锦涛");
        mStringAdapter.setData(texts);
        mStringAdapter.notifyDataSetChanged();

        // 监听单击事件
        mStringAdapter.setOnItemClickListener((view, position, datum) -> {
            // 其中datum就是点击的ITEM对应的实体，再也不用手动去获取了
            Toast.makeText(MainActivity.this, "单击了：" + datum, Toast.LENGTH_SHORT).show();
        });

        // 监听长按时间
        mStringAdapter.setOnItemLongClickListener((view, position, datum) -> {
            Toast.makeText(MainActivity.this, "长按了：" + datum, Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
