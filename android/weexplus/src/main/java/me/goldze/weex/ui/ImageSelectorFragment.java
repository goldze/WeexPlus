package me.goldze.weex.ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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
 * 该页面对用户完全透明，作为路由处理图片选择
 * Created by goldze on 2018/6/1 0001.
 */

public class ImageSelectorFragment extends Fragment {
    private final int RESULT_CODE = 0001;
    private PublishSubject<ImageSelectorBean> subject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setSubjectForImageSelectorBean(@NonNull PublishSubject<ImageSelectorBean> subject) {
        this.subject = subject;
    }

    public PublishSubject<ImageSelectorBean> getSubjectByImageSelectorBean() {
        return this.subject;
    }


    public void openImageSelector() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new WeexGlideEngine())
                .forResult(RESULT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE && null != data) {
            ArrayList<Uri> mSelected = (ArrayList<Uri>) Matisse.obtainResult(data);
            ImageSelectorBean bean = new ImageSelectorBean();
            bean.setUris(mSelected);
            subject.onNext(bean);
            subject.onComplete();
        }
    }
}
