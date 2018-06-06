package me.goldze.weex.natives.module.qrcode;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import me.goldze.weex.event.bean.WeexEventBean;
import me.goldze.weex.event.constant.EventConstant;
import me.goldze.weex.natives.base.BaseWeexModule;
import me.goldze.weex.natives.base.ImageSelectorBean;
import me.goldze.weex.ui.WeexPagerActivity;
import me.goldze.weex.utils.UriConvert;

/**
 * 二维码
 * Created by goldze on 2018/3/29 0029.
 */

public class QRCodeModule extends BaseWeexModule {
    @Override
    public void register() {
        super.register(EventConstant.ACTION.SCANNING_QR, new Consumer<WeexEventBean>() {
            @Override
            public void accept(WeexEventBean weexEventBean) throws Exception {
                scanningQR(weexEventBean);
            }
        });
        //生成二维码，功能暂时屏蔽，大多数生成二维码的需求是由服务端实现
//        super.register(EventConstant.ACTION.PRODUCE_QR, new Consumer<WeexEventBean>() {
//            @Override
//            public void accept(WeexEventBean weexEventBean) throws Exception {
//                produceQR(weexEventBean);
//            }
//        });
    }

    /**
     * 扫描二维码
     *
     * @param weexEventBean
     */
    private void scanningQR(final WeexEventBean weexEventBean) {
        new RxPermissions((Activity) weexEventBean.getContext())
                .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            startCaptureActivity(weexEventBean);
                        } else {
                            //相机权限打开失败
                            callbackFail(weexEventBean);
                        }
                    }
                });
    }

    /**
     * 生成二维码
     *
     * @param weexEventBean
     */
    private void produceQR(WeexEventBean weexEventBean) {
        try {
            String str = (String) weexEventBean.getParams().get("body");
            Bitmap bitmap = createBitmap(str);
            Map<String, Object> data = new HashMap<>();
            data.put("bitmap", bitmap);
            callbackSuccess(weexEventBean, data);
        } catch (Exception e) {
            callbackFail(weexEventBean);
        }
    }

    /**
     * 传入一串字符串，生成二维码Bitmap
     *
     * @param str
     * @return
     */
    public Bitmap createBitmap(String str) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 400, 400);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(result);
        return bitmap;
    }

    /**
     * 打开二维码扫描界面
     *
     * @param weexEventBean
     */
    private void startCaptureActivity(final WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        //RxJava操作图片选择
        new QRCodeRequest((WeexPagerActivity) context)
                .request()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String scanResult) throws Exception {
                        if (!TextUtils.isEmpty(scanResult)) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("result", scanResult);
                            callbackSuccess(weexEventBean, data);
                        } else {
                            callbackFail(weexEventBean);
                        }
                    }
                });
    }

}
