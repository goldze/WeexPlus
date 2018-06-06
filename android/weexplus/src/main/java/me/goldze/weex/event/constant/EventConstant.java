package me.goldze.weex.event.constant;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class EventConstant {
    /**
     * Weex-Native的事件传递的动作
     */
    public static class ACTION {
        //打开新页面
        public static final String START_PAGER = "START_PAGER";
        //关闭当前界面
        public static final String CLOSE_PAGER = "CLOSE_PAGER";
        //读取SP数据
        public static final String READ_DATA = "READ_DATA";
        //写入SP数据
        public static final String WRITE_DATA = "WRITE_DATA";
        //请求定位经纬度
        public static final String REQUEST_LOCATION = "REQUEST_LOCATION";
        //扫描二维码
        public static final String SCANNING_QR = "SCANNING_QR";
        //生成二维码
        public static final String PRODUCE_QR = "PRODUCE_QR";
        //选择图片
        public static final String IMAGE_SELECT = "IMAGE_SELECT";
    }

    /**
     * Native-Weex的事件传递唯一标识
     */
    public static class CALLBACK {
        //打开页面
        public static final String OPEN_PAGER = "OPEN_PAGER";
        //关闭页面
        public static final String CLOSE_PAGER = "CLOSE_PAGER";
    }
}
