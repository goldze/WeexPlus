package me.goldze.weex;

import android.app.Application;
import android.content.Context;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import me.goldze.weex.adapter.ImageAdapter;
import me.goldze.weex.event.WeexEventManager;
import me.goldze.weex.extend.module.AppModule;
import me.goldze.weex.extend.module.ToastModule;

/**
 * Created by goldze on 2018/3/2 0002.
 */

public class WeexApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize(this);
    }

    public static void initialize(Application application) {
        mContext = application;
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(application, config);
        try {
            WXSDKEngine.registerModule("ToastModule", ToastModule.class);
            WXSDKEngine.registerModule("AppModule", AppModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        WeexEventManager.getInstance().initEvent();
    }
}
