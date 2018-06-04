package me.goldze.weex.natives.base;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by goldze on 2018/6/1 0001.
 */

public class ImageSelectorBean implements Serializable, Parcelable {
    private ArrayList<Uri> uris;

    public ArrayList<Uri> getUris() {
        return uris;
    }

    public void setUris(ArrayList<Uri> uris) {
        this.uris = uris;
    }

    public ImageSelectorBean() {

    }

    protected ImageSelectorBean(Parcel in) {
        uris = in.createTypedArrayList(Uri.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(uris);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageSelectorBean> CREATOR = new Creator<ImageSelectorBean>() {
        @Override
        public ImageSelectorBean createFromParcel(Parcel in) {
            return new ImageSelectorBean(in);
        }

        @Override
        public ImageSelectorBean[] newArray(int size) {
            return new ImageSelectorBean[size];
        }
    };
}
