package me.goldze.weex.natives.base;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public interface IBaseWeexModule {
    /**
     * 注册监听
     */
    void register();

    /**
     * 解除注册
     */
    void unregister();
}
