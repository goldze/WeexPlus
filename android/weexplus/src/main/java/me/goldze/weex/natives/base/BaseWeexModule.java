package me.goldze.weex.natives.base;

import com.taobao.weex.bridge.JSCallback;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.event.rx.RxBus;
import me.goldze.weex.event.rx.RxSubscriptions;

/**
 * Model的公共基类，每个Model中维护了一个被观察者
 * Created by goldze on 2018/3/28 0028.
 */

public abstract class BaseWeexModule implements IBaseWeexModule {

    private Disposable subscribe;

    public void register(String token, Consumer<WeexEventBean> action) {
        //RxBus注册监听
        subscribe = RxBus.getDefault().toObservable(WeexEventBean.class)
                .filter(new WeexEventActionPredicate(token)) // 过滤token
                .subscribe(action);
        //将订阅者加入管理站
        RxSubscriptions.add(subscribe);
    }

    public void unregister() {
        RxSubscriptions.remove(subscribe);
        subscribe = null;
    }

    /**
     * 回调Weex成功方法，通知一次
     *
     * @param weexEventBean event实体
     */
    protected void callbackSuccess(WeexEventBean weexEventBean) {
        callbackSuccess(weexEventBean, null);
    }

    /**
     * 回调Weex成功方法，通知一次
     *
     * @param weexEventBean event实体
     * @param data          回传的参数
     */
    protected void callbackSuccess(WeexEventBean weexEventBean, Map<String, Object> data) {
        List<JSCallback> callbacks = weexEventBean.getJsCallbacks();
        if (callbacks != null && callbacks.size() > 0) {
            weexEventBean.getJsCallbacks().get(0).invoke(data);
        }
    }

    /**
     * 回调Weex失败方法，通知一次
     *
     * @param weexEventBean event实体
     */
    protected void callbackFail(WeexEventBean weexEventBean) {
        callbackFail(weexEventBean, null);
    }

    /**
     * 回调Weex失败方法，通知一次
     *
     * @param weexEventBean event实体
     * @param data          回传的参数
     */
    protected void callbackFail(WeexEventBean weexEventBean, Map<String, Object> data) {
        List<JSCallback> callbacks = weexEventBean.getJsCallbacks();
        if (callbacks != null && callbacks.size() > 1) {
            weexEventBean.getJsCallbacks().get(1).invoke(data);
        }
    }

}
