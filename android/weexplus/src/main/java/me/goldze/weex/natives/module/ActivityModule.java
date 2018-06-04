package me.goldze.weex.natives.module;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.event.constant.EventConstant;
import me.goldze.weex.natives.base.BaseWeexModule;
import me.goldze.weex.ui.WeexPagerActivity;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class ActivityModule extends BaseWeexModule {
    @Override
    public void register() {
        super.register(EventConstant.ACTION.START_PAGER, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                startActivity(weexEventBean);
            }
        });
        super.register(EventConstant.ACTION.CLOSE_PAGER, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                finish(weexEventBean);
            }
        });
    }

    /**
     * 开启一个新页面
     *
     * @param weexEventBean
     */
    private void startActivity(WeexEventBean weexEventBean) {
        //打开页面
        Map<String, Object> params = weexEventBean.getParams();
        if (!TextUtils.isEmpty((String) params.get("url"))) {
            Intent intent = new Intent(weexEventBean.getContext(), WeexPagerActivity.class);
            intent.putExtra("params", new HashMap(params));
            weexEventBean.getContext().startActivity(intent);
            //回调成功的方法, 不带参数
            callbackSuccess(weexEventBean);
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("error", "页面打开失败，url为空!");
            //回调失败的方法, 不带参数
            callbackFail(weexEventBean, data);
        }
    }

    /**
     * 关闭界面
     *
     * @param weexEventBean
     */
    private void finish(WeexEventBean weexEventBean) {
        if (weexEventBean.getContext() instanceof Activity) {
            ((Activity) weexEventBean.getContext()).finish();
            callbackSuccess(weexEventBean);
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("error", "页面关闭失败，未知异常!");
            //回调失败的方法, 不带参数
            callbackFail(weexEventBean, data);
        }
    }
}
