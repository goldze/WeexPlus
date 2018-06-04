package me.goldze.weex.extend.module;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by goldze on 2018/3/8 0008.
 */

public class ToastModule extends WXModule {
    //run ui thread
    @JSMethod(uiThread = true)
    public void showShort(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //run ui thread
    @JSMethod(uiThread = true)
    public void showLong(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
