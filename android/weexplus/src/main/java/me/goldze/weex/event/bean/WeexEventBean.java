package me.goldze.weex.event.bean;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.taobao.weex.bridge.JSCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class WeexEventBean implements Serializable, Parcelable {
    private String action;
    private Map<String, Object> params;
    //TODO 这里考虑使用其他集合结构
    private List<JSCallback> jsCallbacks;
    private Context context;

    public WeexEventBean(String action, Map<String, Object> params, List<JSCallback> jsCallbacks, Context context) {
        this.action = action;
        this.params = params;
        this.jsCallbacks = jsCallbacks;
        this.context = context;
    }

    public WeexEventBean() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<JSCallback> getJsCallbacks() {
        return jsCallbacks;
    }

    public void setJsCallbacks(List<JSCallback> jsCallbacks) {
        this.jsCallbacks = jsCallbacks;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.action);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
        }
        dest.writeList(this.jsCallbacks);
    }

    protected WeexEventBean(Parcel in) {
        this.action = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, Object>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            Object value = in.readParcelable(Object.class.getClassLoader());
            this.params.put(key, value);
        }
        this.jsCallbacks = new ArrayList<JSCallback>();
        in.readList(this.jsCallbacks, JSCallback.class.getClassLoader());
        this.context = in.readParcelable(Context.class.getClassLoader());
    }

    public static final Creator<WeexEventBean> CREATOR = new Creator<WeexEventBean>() {
        @Override
        public WeexEventBean createFromParcel(Parcel source) {
            return new WeexEventBean(source);
        }

        @Override
        public WeexEventBean[] newArray(int size) {
            return new WeexEventBean[size];
        }
    };
}
