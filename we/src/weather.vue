<template>
  <div class="divMain">
    <image class="backStyle" :src="backImg" />
    <div>
      <wxc-button class="btnStyle" text="选择城市" @wxcButtonClicked="selectCity"></wxc-button>
    </div>
    <div class="divCenterBody">
      <input class="inputStyle" type="text" placeholder="请输入城市名称" v-model="cityName" />
      <wxc-button class="btnStyle" text="查询" @wxcButtonClicked="queryWeather"></wxc-button>
    </div>
    <wxc-loading :show="isShow" type="trip"></wxc-loading>
    <wxc-city ref="wxcCity"
              :animationType="animationType"
              :currentLocation="location"
              :cityStyleType="cityStyleType"
              :sourceData="sourceData"
              @wxcCityItemSelected="selectCityResponse"
              @wxcCityOnInput="onCityInput"></wxc-city>

  </div>
</template>

<script>
//网络请求
const stream = weex.requireModule("stream");
//toast、alert等
const model = weex.requireModule("modal");
//自定义的Toast模块
const toastModule = weex.requireModule("ToastModule");
//全局的App原生通信模块
const appModule = weex.requireModule("AppModule");
//导入weex-ui包
import { WxcButton, WxcLoading, WxcCity } from "weex-ui";
//导入base,js
import { getUrl } from "./utils/base.js";
//注意路径
import sourceData from "../res/value/city_data.js";
export default {
  //注册组件
  components: {
    WxcButton,
    WxcLoading,
    WxcCity
  },
  data: {
    isShow: false,
    backImg: "../res/drawable/weatherback.jpg",
    cityName: "",
    animationType: "push",
    sourceData,
    cityStyleType: "list",
    location: "定位中"
  },
  //页面初始化调用
  created: function() {
    var _this = this;
    //读取查询记录
    appModule.event(
      "READ_DATA",
      {
        key: "cityName"
      },
      function(e) {
        _this.cityName = e.value;
      }
    );
  },
  methods: {
    //查询城市天气信息方法
    queryWeather: function() {
      if ("" === this.cityName) {
        toastModule.showShort("查询的城市不能为空!");
        return;
      }
      this.isShow = true;
      try {
        stream.fetch(
          {
            method: "GET",
            type: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:
              "http://api.map.baidu.com/telematics/v3/weather?location='" +
              this.cityName +
              "'&output=json&ak=FA09d4649e228793fd44c9b1c1575e0d"
          },
          res => {
            //隐藏loading
            this.isShow = false;
            var map = {
              data: res.data
            };
            if (res.ok && res.data.error == 0) {
              //跳转天气详情页面
              appModule.event("START_PAGER", {
                url: getUrl(
                  "/dist/weatherDetail.js?weatherJson=" +
                    JSON.stringify(res.data)
                ),
                title: this.cityName,
                data: {}
              });
              //保存查询记录
              appModule.event("WRITE_DATA", {
                key: "cityName",
                value: this.cityName
              });
            } else {
              //弹出toast
              toastModule.showShort("Sorry,操作失败！请检查城市是否正确!");
            }
          }
        );
      } catch (err) {
        //隐藏loading
        this.isShow = false;
        //弹出toast
        toastModule.showShort(
          "Sorry,您的网络出了问题导致操作失败，请检查网络状况!"
        );
        console.log(err);
      }
    },
    //解析经纬度
    parseLngAndLat: function(lngAndLat) {
      try {
        stream.fetch(
          {
            method: "GET",
            type: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:
              "http://api.map.baidu.com/geocoder/v2/?ak=FA09d4649e228793fd44c9b1c1575e0d&location=" +
              lngAndLat.split(",")[1] +
              "," +
              lngAndLat.split(",")[0] +
              "&output=json&pois=1"
          },
          res => {
            if (res.ok) {
              this.location = res.data.result.addressComponent.city;
              if (this.location == "") {
                this.location = "定位失败";
              }
            } else {
              //弹出toast
              this.location = "定位失败";
            }
          }
        );
      } catch (err) {
        //隐藏loading
        this.isShow = false;
        //弹出toast
        toastModule.showShort(
          "Sorry,您的网络出了问题导致操作失败，请检查网络状况!"
        );
        console.log(err);
      }
    },
    //选择城市
    selectCity: function() {
      var _this = this;
      //请求获取经纬度
      appModule.event(
        "REQUEST_LOCATION",
        {},
        function(e) {
          //解析经纬度
          _this.parseLngAndLat(e.lngAndLat);
        },
        function(e) {
          toastModule.showShort("定位失败，请检查权限是否打开!");
        }
      );
      this.cityStyleType = "list";
      this.$refs["wxcCity"].show();
    },
    selectCityResponse(e) {
      if (e.item.name == "定位中" || e.item.name == "定位失败") {
        return;
      }
      this.cityName = e.item.name;
      this.queryWeather();
    },
    onCityInput(e) {}
  }
};
</script>
<style>
.divMain {
  display: flex;
  position: relative;
  /* 水平居中 */
  justify-content: center;
  /* 垂直居中 */
  align-items: center;
}

.backStyle {
  position: absolute;
  top: 0px;
  left: 0px;
  /* 750x1334满屏 */
  width: 750px;
  height: 1334px;
}

.inputStyle {
  placeholder-color: whitesmoke;
  color: whitesmoke;
  padding: 10px;
  background-color: #6f9ceb;
  width: 400px;
  height: 80px;
  border-width: 2px;
  border-radius: 0;
  border-style: solid;
  border-color: #d2dfd2;
  font-size: 32px;
}

.btnStyle {
  font-size: 32px;
  margin: 20px;
  width: 180px;
  height: 80px;
}

.divCenterBody {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
}
</style>