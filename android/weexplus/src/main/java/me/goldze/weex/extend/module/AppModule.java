package me.goldze.weex.extend.module;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.goldze.weex.event.WeexEventManager;
import me.goldze.weex.event.bean.WeexEventBean;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class AppModule extends WXModule {
    //run ui thread
    @JSMethod(uiThread = true)
    public void event(String action, Map<String, Object> params, JSCallback jsCallbackSuccess, JSCallback jsCallbackFail) {
        List<JSCallback> jsCallbacksList = new ArrayList<>();
        if (jsCallbackSuccess != null) {
            jsCallbacksList.add(jsCallbackSuccess);
        }
        if (jsCallbackFail != null) {
            jsCallbacksList.add(jsCallbackFail);
        }
        if (jsCallbacksList.size() == 0) {
            jsCallbacksList = null;
        }
        //构建事件实体Bean，转到业务层处理
        WeexEventManager.getInstance().post(new WeexEventBean(action, params, jsCallbacksList, mWXSDKInstance.getContext()));
    }
}
