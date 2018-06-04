<template>
  <div class="divMain">
    <image class="backStyle" :src="backImg" />
    <div class="space-between-row">
      <text class="textStyle">{{date}}</text>
      <text class="textStyle borderStyle">PM2.5：{{result.pm25}}</text>
    </div>

    <scroller class="scroller" scroll-direction="horizontal">
      <div class="indexStyle" v-for="i in index" :key="i">
        <div class="space-between-row">
          <text class="textStyle font-size-34">{{i.title}}：</text>
          <text class="textStyle font-size-32">{{i.zs}}</text>
        </div>
        <text class="textStyle">　　{{i.des}}</text>
      </div>
    </scroller>

    <div class="div-bottom">
      <div style="flex-direction: row">
        <image :src="c_weather_data.dayPictureUrl" class="imageIconStyle" />
        <text class="textStyle font-size-48 ">{{c_weather_data.weather}}</text>
      </div>
      <div style="flex-direction: row;align-items: flex-end;">
        <text class="textStyle font-size-52 ">{{real}}</text>
        <text class="textStyle font-size-32 ">（{{c_weather_data.temperature}}）</text>
      </div>
      <div>
        <text class="textWindStyle">＞{{c_weather_data.wind}}</text>
      </div>
    </div>
    <scroller class="scroller-gallery" scroll-direction="horizontal">
      <div class="galleryStyle" v-for="(i,index) in weather_data" :key="i" v-if="index>0">
        <div class="space-between-row">
          <text class="textGalleryStyle">{{i.date}}</text>
          <text class="textGalleryStyle">{{i.temperature}}</text>
        </div>
        <div class="space-between-row margin-top-10">
          <text class="textGalleryStyle">{{i.weather}}</text>
          <image :src="i.dayPictureUrl" class="imageGalleryStyle" />
        </div>
      </div>
    </scroller>
  </div>
</template>
<script>
  const navigator = weex.requireModule("navigator");
  //自定义的Toast模块
  const toastModule = weex.requireModule("toastModule");
  //全局事件
  const globalEvent = weex.requireModule("globalEvent");
  //导入base,js
  import {
    getQueryVariable
  } from "./utils/base.js"; //注意路径
  export default {
    data: {
      weatherObj: "",
      result: "",
      index: "",
      weather_data: "",
      c_weather_data: "",
      real: "",
      temperature: "",
      date: "",
      backImg: "../res/drawable/allback.jpg"
    },
    created: function () {
      console.log("创建");
      // var _this = this;
      // globalEvent.addEventListener("init", function(e) {
      //   console.log(e.data);
      //   //初始化数据
      //   _this.initData(e.data);
      // });
    },
    mounted: function () {
      console.log("渲染完毕");
      var weatherJson = getQueryVariable("weatherJson");
      console.log("weatherJson：" + weatherJson);
      this.initData(weatherJson);
    },
    beforeDestroy: function () {
      console.log("销毁");
      globalEvent.removeEventListener("init");
    },
    methods: {
      initData: function (weatherJson) {
        this.weatherObj = JSON.parse(weatherJson);
        this.result = this.weatherObj.results[0];
        this.index = this.result.index;
        this.weather_data = this.result.weather_data;
        this.c_weather_data = this.weather_data[0];
        var arr = this.c_weather_data.date.split("(");
        this.date = arr[0];
        arr = arr[1].split(")");
        this.real = arr[0];
      }
    }
  };
</script>
<style>
  .divMain {
    position: relative;
  }

  .space-between-row {
    justify-content: space-between;
    flex-direction: row;
    align-items: center;
  }

  .backStyle {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 750px;
    height: 1250px;
  }

  .borderStyle {
    border-width: 2px;
    border-radius: 20;
    border-style: solid;
    border-color: #d2dfd2;
  }

  .textStyle {
    padding: 10px;
    margin: 20px;
    color: rgb(255, 255, 255);
    font-size: 28px;
  }

  .scroller {
    margin: 2px;
    border-width: 1px;
    border-style: solid;
    border-color: #d2dfd2;
    background-color: rgba(203, 209, 212, 0.2);
    height: 600px;
    flex-direction: row;
  }

  .indexStyle {
    width: 400px;
    margin: 50px;
  }

  .div-bottom {
    margin: 2px;
    padding: 20px;
    border-width: 1px;
    border-style: solid;
    border-color: #d2dfd2;
    background-color: rgba(203, 209, 212, 0.2);
  }

  .imageIconStyle {
    width: 100px;
    height: 100px;
  }

  .font-size-34 {
    font-size: 34px;
  }

  .font-size-32 {
    font-size: 32px;
  }

  .font-size-48 {
    font-size: 48px;
  }

  .font-size-52 {
    font-family: sans-serif;
    font-size: 52px;
  }

  .scroller-gallery {
    flex-direction: row;
    height: 150px;
    margin: 2px;
    border-width: 1px;
    border-style: solid;
    border-color: #d2dfd2;
    background-color: rgba(203, 209, 212, 0.2);
  }

  .galleryStyle {
    width: 300px;
    padding: 16px;
    border-width: 1px;
    border-style: solid;
    border-color: #d2dfd2;
  }

  .textWindStyle {
    color: rgb(255, 255, 255);
    font-size: 28px;
  }

  .textGalleryStyle {
    color: rgb(255, 255, 255);
    font-size: 28px;
  }

  .imageGalleryStyle {
    width: 60px;
    height: 60px;
  }

  .margin-top-10 {
    margin-top: 30px;
  }
</style>