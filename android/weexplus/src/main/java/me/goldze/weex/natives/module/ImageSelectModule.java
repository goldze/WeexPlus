package me.goldze.weex.natives.module;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.event.constant.EventConstant;
import me.goldze.weex.natives.base.BaseWeexModule;
import me.goldze.weex.natives.base.ImageSelectorBean;
import me.goldze.weex.ui.ImageSelectorFragment;
import me.goldze.weex.ui.WeexPagerActivity;
import me.goldze.weex.utils.UriConvert;

/**
 * Created by goldze on 2018/3/29 0029.
 * 图片选择模块
 */

public class ImageSelectModule extends BaseWeexModule {
    @Override
    public void register() {
        super.register(EventConstant.ACTION.IMAGE_SELECT, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                imageSelect(weexEventBean);
            }
        });
    }

    /**
     * 图片选择
     *
     * @param weexEventBean
     */
    private void imageSelect(final WeexEventBean weexEventBean) {
        new RxPermissions((Activity) weexEventBean.getContext())
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            startImageSelectActivity(weexEventBean);
                        } else {
                            //权限打开失败
                            callbackFail(weexEventBean);
                        }
                    }
                });
    }

    /**
     * 打开图片选择界面
     *
     * @param weexEventBean
     */
    private void startImageSelectActivity(final WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        //RxJava操作图片选择
        new ImageSelector((WeexPagerActivity) context)
                .request()
                .subscribe(new Consumer<ImageSelectorBean>() {
                    @Override
                    public void accept(ImageSelectorBean imageSelectorBean) throws Exception {
                        List<Uri> uris = imageSelectorBean.getUris();
                        if (uris != null && uris.size() > 0) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("imgs", new UriConvert().getImgs(weexEventBean.getContext(), uris));
                            callbackSuccess(weexEventBean, data);
                        } else {
                            callbackFail(weexEventBean);
                        }
                    }
                });
    }

    /**
     * 仿照RxPermissions设计思想的一个图片选择器
     * Created by goldze on 2018/6/1 0001.
     */

    public class ImageSelector {
        private final String TAG = "ImageSelectorFragment";
        private ImageSelectorFragment mImageSelectorFragment;
        private Object TRIGGER = new Object();

        public ImageSelector(@NonNull AppCompatActivity activity) {
            //需要v4兼容包下的Fragment，这里的AppCompatActivity一定是WeexPagerActivity继承的。
            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
            //第一次为空，第二次通过Tag找到Fragment
            ImageSelectorFragment mImageSelectorFragment = (ImageSelectorFragment) supportFragmentManager.findFragmentByTag(TAG);
            if (mImageSelectorFragment == null) {
                mImageSelectorFragment = new ImageSelectorFragment();
                supportFragmentManager
                        .beginTransaction()
                        .add(mImageSelectorFragment, TAG)
                        .commitAllowingStateLoss();
                supportFragmentManager.executePendingTransactions();
            }
            //创建观察者
            mImageSelectorFragment.setSubjectForImageSelectorBean(PublishSubject.<ImageSelectorBean>create());
            this.mImageSelectorFragment = mImageSelectorFragment;
        }

        /**
         * RxJava的方式返回Observable
         *
         * @return 观察者对象
         */
        public Observable<ImageSelectorBean> request() {
            //打开知乎图片选择库
            mImageSelectorFragment.openImageSelector();
            return mImageSelectorFragment.getSubjectByImageSelectorBean();
        }
    }
}
