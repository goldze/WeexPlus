<template>
  <div class="divMain">
    <wxc-button class="btnStyle" text="打开新界面" @wxcButtonClicked="openPager"></wxc-button>
    <wxc-button class="btnStyle" text="关闭界面" @wxcButtonClicked="closePager"></wxc-button>
    <wxc-button class="btnStyle" text="写入磁盘" @wxcButtonClicked="writeData"></wxc-button>
    <wxc-button class="btnStyle" text="读取磁盘" @wxcButtonClicked="readData"></wxc-button>
    <wxc-button class="btnStyle" text="获取经纬度" @wxcButtonClicked="requestLocation"></wxc-button>
    <wxc-button class="btnStyle" text="扫描二维码" @wxcButtonClicked="scanningQRCode"></wxc-button>
    <wxc-button class="btnStyle" text="选择图片" @wxcButtonClicked="imageSelect"></wxc-button>
    <scroller v-if="imgsShow" scroll-direction="horizontal" class="scroller-img">
      <div v-for="i in imgs" :key="i">
        <image class="img-wrapper" :src="i" />
      </div>
    </scroller>
    <wxc-button class="btnStyle" text="天气预报" @wxcButtonClicked="openWeather"></wxc-button>
  </div>
</template>

<script>
//导入weex-ui包
import { WxcButton } from "weex-ui";
//网络请求
const stream = weex.requireModule("stream");
//全局的App原生通信模块
const appModule = weex.requireModule("AppModule");
//自定义的Toast模块
const toastModule = weex.requireModule("ToastModule");
//导入base,js
import { geRootIp } from "./utils/base.js";
export default {
  components: {
    WxcButton
  },
  data: {
    imgs: "",
    imgsShow: false,
    bitmap: ""
  },
  methods: {
    //打开新页面
    openPager: function() {
      appModule.event(
        "START_PAGER",
        {
          url: geRootIp() + "/dist/index.js",
          title: "新页面",
          data: {}
        },
        function(e) {
          toastModule.showShort("打开页面成功!");
        },
        function(e) {
          toastModule.showShort("打开页面失败!");
        }
      );
    },
    //关闭新页面
    closePager: function() {
      appModule.event(
        "CLOSE_PAGER",
        {},
        function(e) {
          toastModule.showShort("关闭页面成功!");
        },
        function(e) {
          toastModule.showShort(e.error);
        }
      );
    },
    //写入数据
    writeData: function() {
      appModule.event(
        "WRITE_DATA",
        {
          key: "user_info",
          value: "{'userName':'张三','age':'18岁'}"
        },
        function(e) {
          toastModule.showShort("写入成功!");
        }
      );
    },
    //读取数据
    readData: function() {
      appModule.event(
        "READ_DATA",
        {
          key: "user_info"
        },
        function(e) {
          toastModule.showShort(e.value);
        }
      );
    },
    //请求获取经纬度
    requestLocation: function() {
      appModule.event(
        "REQUEST_LOCATION",
        {},
        function(e) {
          toastModule.showShort(e.lngAndLat);
        },
        function(e) {
          toastModule.showShort("定位失败，请检查权限是否打开!");
        }
      );
    },
    //扫描二维码
    scanningQRCode: function() {
      appModule.event(
        "SCANNING_QR",
        {},
        function(e) {
          toastModule.showShort(e.result);
        },
        function(e) {
          toastModule.showShort("扫描失败，请检查权限是否打开!");
        }
      );
    },
    //生成二维码
    produceQRCode: function() {
      var _this = this;
      appModule.event(
        "PRODUCE_QR",
        {
          body: "https://github.com/goldze/WeexPlus"
        },
        function(e) {
          toastModule.showShort("生成成功!");
          _this.bitmap = e.bitmap;
        },
        function(e) {
          toastModule.showShort("二维码生成失败!");
        }
      );
    },
    //选择图片
    imageSelect: function() {
      var _this = this;
      appModule.event(
        "IMAGE_SELECT",
        {},
        function(e) {
          _this.imgsShow = true;
          _this.imgs = e.imgs;
          toastModule.showShort("选择了" + e.imgs.length + "张照片");
        },
        function(e) {
          toastModule.showShort("图片选择失败!");
        }
      );
    },
    //打开天气预报界面
    openWeather: function() {
      appModule.event("START_PAGER", {
        url: geRootIp() + "/dist/weather.js",
        title: "NO_NAVIGATION",
        data: {}
      });
    }
  }
};
</script>

<style>
.btnStyle {
  margin-top: 10px;
}

.img-wrapper {
  margin: 10px;
  width: 100px;
  height: 100px;
}

.wrapper {
  background-color: beige;
  justify-content: center;
  align-items: center;
}

.scroller-img {
  height: 100px;
  margin: 10px;
  background-color: rgba(203, 209, 212, 0.2);
  flex-direction: row;
}

.divMain {
  align-items: center;
}
</style>