package com.example.test03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1Activity extends Activity {
    // 加载列表
    private String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    // 加载资源
    private int[] images = {
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_test1);

        ListView listView = findViewById(R.id.listView);
        TextView selectedItem = findViewById(R.id.button_back);

        // 准备数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", names[i]);
            map.put("image", images[i]);
            data.add(map);
        }

        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"name", "image"},
                new int[]{R.id.item_text, R.id.item_image}
        );

        // 设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names[position];
                // 显示 Toast
                Toast.makeText(Test3Activity.this, name, Toast.LENGTH_SHORT).show();
                // 更新选中的项目
                selectedItem.setText(name);
            }
        });
    }
}
