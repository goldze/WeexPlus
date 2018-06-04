package me.goldze.weex.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;
import java.util.Map;

import me.goldze.weex.R;
import me.goldze.weex.widget.LoadingLayout;

public class WeexPagerActivity extends AppCompatActivity implements IWXRenderListener {
    public static final String NO_NAVIGATION = "NO_NAVIGATION";
    private WXSDKInstance mWXSDKInstance;
    private ViewGroup contentView;
    private LoadingLayout loadingLayout;
    private HashMap<String, Object> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex_pager);
        //初始化控件
        initView();
        //初始化数据
        initData();
        WXEnvironment.isPerf = true;
        WXSDKEngine.addCustomOptions("appName", "WXSample");
        WXSDKEngine.addCustomOptions("appGroup", "WXApp");
        //渲染页面
        render();
    }

    private void initData() {
        params = (HashMap<String, Object>) getIntent().getSerializableExtra("params");
        String title = (String) params.get("title");
        if (NO_NAVIGATION.equals(title)) {
            findViewById(R.id.include).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.include).findViewById(R.id.tv_title)).setText(title);
        }
    }

    private void initView() {
        contentView = findViewById(R.id.content_view);
        loadingLayout = findViewById(R.id.loadingLayout);
        findViewById(R.id.include).findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadingLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                render();
            }
        });
    }

    /**
     * 渲染页面
     */
    public void render() {
        final String url = (String) params.get("url");
        if (TextUtils.isEmpty(url)) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("功能完善中，敬请期待!")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .create().show();
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setErrorType(LoadingLayout.NETWORK_LOADING);
                mWXSDKInstance = new WXSDKInstance(WeexPagerActivity.this);
                mWXSDKInstance.registerRenderListener(WeexPagerActivity.this);
                Map<String, Object> options = new HashMap<>();
                options.put(WXSDKInstance.BUNDLE_URL, url);
                if (url.contains("http://") || url.contains("https://")) {
                    /**
                     * pageName:自定义，一个标示符号。
                     * url:远程bundle JS的下载地址
                     * options:初始化时传入WEEX的参数，比如 bundle JS地址
                     * flag:渲染策略。WXRenderStrategy.APPEND_ASYNC:异步策略先返回外层View，其他View渲染完成后调用onRenderSuccess。WXRenderStrategy.APPEND_ONCE 所有控件渲染完后后一次性返回。
                     */
                    mWXSDKInstance.renderByUrl("WeexPagerActivity", url, options, null, WXRenderStrategy.APPEND_ASYNC);
                } else if (url.contains("file://")) {
                    /**
                     * pageName:自定义，一个标示符号。
                     * template:js的数据流，一般用于加载本地assets目录下的js文件
                     * options:初始化时传入WEEX的参数，比如 bundle JS地址
                     * flag:渲染策略。WXRenderStrategy.APPEND_ASYNC:异步策略先返回外层View，其他View渲染完成后调用onRenderSuccess。WXRenderStrategy.APPEND_ONCE 所有控件渲染完后后一次性返回。
                     */
                    mWXSDKInstance.render("ContainerActivitsy", WXFileUtils.loadAsset(url.split("file://")[1], WeexPagerActivity.this), options, null, WXRenderStrategy.APPEND_ASYNC);
                }
            }
        });
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        //界面没有销毁则加载
        if (!this.isFinishing()) {
            //隐藏布局
            loadingLayout.setErrorType(LoadingLayout.HIDE_LAYOUT);
            contentView.removeAllViews();
            contentView.addView(view);
        }
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        Map<String, Object> data = (Map<String, Object>) params.get("data");
        if (data == null || data.size() == 0) {
            //发送事件通知weex调用
            instance.fireGlobalEventCallback("init", data);
        }
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        //显示网络错误
        loadingLayout.setErrorType(LoadingLayout.NETWORK_ERROR);
        String errMsg;
        if ("wx_network_error".equals(errCode)) {
            errMsg = "网络错误，请检查网络连接是否正常！";
        } else if ("-2013".equals(errCode)) {
            errMsg = "服务器内部错误！";
        } else {
            errMsg = "未知错误";
        }
        loadingLayout.setErrorMessage(errMsg);
        Log.e("WeexPagerActivity", msg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
