package me.goldze.weex.ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;

import io.reactivex.subjects.PublishSubject;
import me.goldze.weex.R;
import me.goldze.weex.adapter.GifSizeFilter;
import me.goldze.weex.adapter.WeexGlideEngine;
import me.goldze.weex.natives.base.ImageSelectorBean;

/**
 * 该页面对用户完全透明，作为路由处理扫描二维码的返回结果
 * Created by goldze on 2018/6/1 0001.
 */

public class QRCodeRequestFragment extends Fragment {
    private final int RESULT_CODE = 0001;
    private PublishSubject<String> subject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setSubjectForString(@NonNull PublishSubject<String> subject) {
        this.subject = subject;
    }

    public PublishSubject<String> getSubjectByString() {
        return this.subject;
    }


    public void openImageSelector() {
        new IntentIntegrator(this.getActivity())
                .forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setOrientationLocked(true)
                .initiateScan(); // 初始化扫描
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String ScanResult = "";
        if (intentResult != null) {
            if (intentResult.getContents() != null) {
                // ScanResult 为 获取到的字符串
                ScanResult = intentResult.getContents();
            }
        }
        subject.onNext(ScanResult);
        subject.onComplete();
    }
}
