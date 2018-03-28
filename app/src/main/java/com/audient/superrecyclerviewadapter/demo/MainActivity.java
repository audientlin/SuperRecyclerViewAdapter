package com.audient.superrecyclerviewadapter.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.audient.superrecyclerviewadapter.demo.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;

    private StringAdapter mStringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStringAdapter = new StringAdapter(this);
        mBinding.recyclerView.setAdapter(mStringAdapter);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

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
