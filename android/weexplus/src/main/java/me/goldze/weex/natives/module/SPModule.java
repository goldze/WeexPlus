package me.goldze.weex.natives.module;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.event.constant.EventConstant;
import me.goldze.weex.natives.base.BaseWeexModule;
import me.goldze.weex.utils.SPUtils;

/**
 * Created by goldze on 2018/3/29 0029.
 * 本身weex提供了storage模块, 为什么这里又要自己写一个SharedPreferences存储呢？为的是weex与原生更好的通信。
 * 比如混合开发时, 登录界面是原生界面，登录成功后本地保存用户唯一标识，当进入weex界面时可以通过该模块取出用户唯一标识，
 * 实现相应逻辑
 */

public class SPModule extends BaseWeexModule {
    @Override
    public void register() {
        super.register(EventConstant.ACTION.READ_DATA, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                readData(weexEventBean);
            }
        });
        super.register(EventConstant.ACTION.WRITE_DATA, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                writeData(weexEventBean);
            }
        });
    }

    /**
     * 写入SharedPreferences
     *
     * @param weexEventBean
     */
    private void writeData(WeexEventBean weexEventBean) {
        Map<String, Object> params = weexEventBean.getParams();
        String key = (String) params.get("key");
        String value = (String) params.get("value");
        SPUtils.getInstance().put(key, value);
        callbackSuccess(weexEventBean);
    }

    /**
     * 读取SharedPreferences
     *
     * @param weexEventBean
     */
    private void readData(WeexEventBean weexEventBean) {
        Map<String, Object> params = weexEventBean.getParams();
        String key = (String) params.get("key");
        String value = SPUtils.getInstance().getString(key);
        Map<String, Object> data = new HashMap<>();
        data.put("value", value);
        callbackSuccess(weexEventBean, data);
    }
}
