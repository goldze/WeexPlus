package com.goldze.weex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import me.goldze.weex.ui.WeexPagerActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", getResources().getString(R.string.entrance_url));
        params.put("title", WeexPagerActivity.NO_NAVIGATION);
        Intent intent = new Intent(MainActivity.this, WeexPagerActivity.class);
        intent.putExtra("params", params);
        startActivity(intent);
        finish();
    }
}
