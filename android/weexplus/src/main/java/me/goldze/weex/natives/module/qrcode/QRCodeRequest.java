package me.goldze.weex.natives.module.qrcode;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import me.goldze.weex.ui.QRCodeRequestFragment;

/**
 * 仿照RxPermissions设计思想的一个二维码界面请求
 * Created by goldze on 2018/6/1 0001.
 */

public class QRCodeRequest {
    private final String TAG = "QRCodeRequestFragment";
    private QRCodeRequestFragment mQRRequestFragment;
    private Object TRIGGER = new Object();

    public QRCodeRequest(@NonNull AppCompatActivity activity) {
        //需要v4兼容包下的Fragment，这里的AppCompatActivity一定是WeexPagerActivity继承的。
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        //第一次为空，第二次通过Tag找到Fragment
        QRCodeRequestFragment mQRRequestFragment = (QRCodeRequestFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (mQRRequestFragment == null) {
            mQRRequestFragment = new QRCodeRequestFragment();
            supportFragmentManager
                    .beginTransaction()
                    .add(mQRRequestFragment, TAG)
                    .commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }
        //创建观察者
        mQRRequestFragment.setSubjectForString(PublishSubject.<String>create());
        this.mQRRequestFragment = mQRRequestFragment;
    }

    /**
     * RxJava的方式返回Observable
     *
     * @return 观察者对象
     */
    public Observable<String> request() {
        //打开zxing二维码扫描
        mQRRequestFragment.openImageSelector();
        return mQRRequestFragment.getSubjectByString();
    }
}
