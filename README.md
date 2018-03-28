# SuperRecyclerViewAdapter
一个超级好用的RecyclerViewAdapter。

## 一、引入

```groovy
// 在 project 根目录的 build.gradle 中添加：
allprojects {
    repositories {
        maven { url "https://raw.githubusercontent.com/AudienL/repos/master" }
    }
}

// 在 module 根目录的 build.gradle 中添加：
dependencies {
    implementation "com.audient:super_recycler_view_adapter:1.0.2"
}
```

## 二、使用

##### 继承`SuperRecyclerViewAdapter`，并实现相应方法

```java
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
```

##### 编写布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="10dp">

    <TextView
        android:id="@+id/tv_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        tools:text="景清清"/>
</RelativeLayout>
```

##### Activity里面使用

```java
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
```

## 三、如果你使用了DataBinding

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable name="text" type="String"/>
    </data>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp">
        <TextView
            android:id="@+id/tv_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{text}"
            tools:text="景清清"/>
    </RelativeLayout>
</layout>
```

```java
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
```

调用方法一样。