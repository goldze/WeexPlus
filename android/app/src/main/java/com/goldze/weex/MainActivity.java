package com.goldze.weex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import me.goldze.weex.ui.WeexPagerActivity;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText et = findViewById(R.id.et_url);
        sp = getSharedPreferences("url", Context.MODE_PRIVATE);
        String url = sp.getString("url_info", "");
        if (TextUtils.isEmpty(url)) {
            et.setText("http://192.168.17.199:8082/dist/weather.js");
        } else {
            et.setText(url);
        }

        findViewById(R.id.tv_right_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = et.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(MainActivity.this, "url不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> params = new HashMap<>();
                params.put("url", url);
                params.put("title", WeexPagerActivity.NO_NAVIGATION);
                Intent intent = new Intent(MainActivity.this, WeexPagerActivity.class);
                intent.putExtra("params", params);
                startActivity(intent);
                sp.edit().putString("url_info", url).commit();
            }
        });
    }
}
