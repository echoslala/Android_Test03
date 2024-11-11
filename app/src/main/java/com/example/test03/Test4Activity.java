package com.example.test03;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Test4Activity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private String[] items = {"One", "Two", "Three", "Four", "Five"};
    private ArrayList<Boolean> checkedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmode_test4);

        listView = findViewById(R.id.listView);

        // 初始化检查状态
        checkedItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            checkedItems.add(false);
        }

        // 设置自定义适配器
        adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        // 设置多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 切换选中状态
                checkedItems.set(position, !checkedItems.get(position));
                adapter.notifyDataSetChanged();  // 刷新背景颜色
            }
        });

        // 添加长按监听器
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 切换选中状态
                checkedItems.set(position, !checkedItems.get(position));
                adapter.notifyDataSetChanged();  // 刷新背景颜色
                return true;
            }
        });

        // 添加ActionMode回调
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 切换选中状态
                checkedItems.set(position, !checkedItems.get(position));
                adapter.notifyDataSetChanged();  // 刷新背景颜色
                return true;
            }
        });
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        // 加载上下文菜单
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_main, mode.getMenu());
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.button_back) {
            // 删除选中的项
            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                if (checkedItems.get(i)) {
                    adapter.remove(i);
                    checkedItems.remove(i);
                }
            }
            adapter.notifyDataSetChanged();
            mode.finish(); // 退出上下文操作模式
            Toast.makeText(Test4Activity.this, "删除成功", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onActionModeFinished(mode, item);
    }

    // 自定义适配器类
    private class CustomAdapter extends ArrayAdapter<String> {

        CustomAdapter() {
            super(Test4Activity.this, R.layout.list_item, items);
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            // 设置图标
            ImageView icon = convertView.findViewById(R.id.item_icon);
            icon.setImageResource(R.drawable.ic_launcher_foreground);

            // 设置文本
            TextView text = convertView.findViewById(R.id.item_text);
            text.setText(items[position]);

            // 根据选中状态设置背景颜色
            if (checkedItems.get(position)) {
                convertView.setBackgroundColor(Color.LTGRAY);  // 设置选中项背景颜色
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);  // 未选中的项背景
            }

            return convertView;
        }

        // 删除列表项的方法
        void remove(int position) {
            ArrayList<String> tempList = new ArrayList<>();
            for (String item : items) {
                tempList.add(item);
            }
            tempList.remove(position);
            items = tempList.toArray(new String[0]);
            notifyDataSetChanged();
        }
    }
}
