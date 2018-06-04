package me.goldze.weex.event;

import java.util.ArrayList;
import java.util.List;

import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.natives.base.IBaseWeexModule;
import me.goldze.weex.natives.module.ActivityModule;
import me.goldze.weex.natives.module.ImageSelectModule;
import me.goldze.weex.natives.module.LocationModule;
import me.goldze.weex.event.rx.RxBus;
import me.goldze.weex.natives.module.SPModule;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class WeexEventManager {
    //TODO 这里考虑使用其他集合结构
    private List<IBaseWeexModule> modules;

    private WeexEventManager() {
        modules = new ArrayList<>();
        //添加页面模块
        modules.add(new ActivityModule());
        //添加定位模块
        modules.add(new LocationModule());
        //添加SharedPreferences模块
        modules.add(new SPModule());
        //添加图片选择模块
        modules.add(new ImageSelectModule());
    }

    public static WeexEventManager instance;

    public static WeexEventManager getInstance() {
        synchronized (WeexEventManager.class) {
            if (instance == null) {
                instance = new WeexEventManager();
            }
        }
        return instance;
    }

    /**
     * 初始化注册Weex-Native事件(可根据需求注册，比如混合开发，当点入Weex业务模块时开启注册，退出时解除注册，优化性能)
     */
    public void initEvent() {
        if (modules.size() != 0) {
            for (IBaseWeexModule module : modules) {
                module.register();
            }
        }
    }

    /**
     * 解除注册事件
     */
    public void unEvent() {
        if (modules.size() != 0) {
            for (IBaseWeexModule module : modules) {
                module.unregister();
            }
        }
    }

    public void post(WeexEventBean bean) {
        RxBus.getDefault().post(bean);
    }
}
