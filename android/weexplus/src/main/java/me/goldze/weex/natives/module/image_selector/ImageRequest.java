package me.goldze.weex.natives.module.image_selector;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import me.goldze.weex.natives.base.ImageSelectorBean;
import me.goldze.weex.ui.ImageRequestFragment;

/**
 * 仿照RxPermissions设计思想的一个图片选择界面请求
 * Created by goldze on 2018/6/1 0001.
 */

public class ImageRequest {
    private final String TAG = "ImageRequestFragment";
    private ImageRequestFragment mImageSelectorFragment;
    private Object TRIGGER = new Object();

    public ImageRequest(@NonNull AppCompatActivity activity) {
        //需要v4兼容包下的Fragment，这里的AppCompatActivity一定是WeexPagerActivity继承的。
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        //第一次为空，第二次通过Tag找到Fragment
        ImageRequestFragment mImageSelectorFragment = (ImageRequestFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (mImageSelectorFragment == null) {
            mImageSelectorFragment = new ImageRequestFragment();
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
