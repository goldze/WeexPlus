package com.goldze.weex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import me.goldze.weex.ui.WeexPagerActivity;

public class MainActivity extends AppCompatActivity {
    //换成你自己的第一个页面文件，可以是本地, 可以是网络
    public static final String URL = "file://main_demo.js";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", URL);
        params.put("title", WeexPagerActivity.NO_NAVIGATION);
        Intent intent = new Intent(MainActivity.this, WeexPagerActivity.class);
        intent.putExtra("params", params);
        startActivity(intent);
        finish();
    }
}
