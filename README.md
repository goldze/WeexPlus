# WeexPlus

为解决目前移动开发频繁的迭代、开发周期长、人员成本高的问题，移动跨平台开发方案层出不穷。 **WeexPlus则是一款基于阿里[weex](http://weex.apache.org/cn/)跨平台方案(android/ios/h5)开发的weex端与android native交互的组件扩展库，提供页面导航、数据存储、图片选择、二维码识别/生成、权限等原生功能**。web开发者不用再为调用native功能而烦恼，android开发者轻松搞定hybrid开发。

## 框架流程



## 框架特点
- **快速集成**

	不管是web开发者，还是android开发者，都可以快速的集成此库。

- **事件模式**

	整体使用事件派发思想，weex与native完全解耦，相互不依赖。weex端指定唯一的Action(动作)，native端会根据Action做出判断，执行对应的逻辑，响应结果。

- **页面加载器**

	WeexPlus提供一个原生的页面加载器，只需要传入JSBundle文件路径(本地/网络都可以)，会自动渲染页面，并带有material design加载效果。加载失败显示错误信息，可点击重新加载。

## 1、准备工作
> WeexPlus基于Weex技术，Weex 是一个使用 Web 开发体验来开发高性能原生应用的框架，所以你需要掌握一定的Weex开发知识。[相关文档](#xgwd)

根据官方介绍配置安装weex开发环境

### 1.1、android视角
WeexPlus是集成了[weex_sdk](https://github.com/apache/incubator-weex)、[glide](https://github.com/bumptech/glide)、[rxpermissions2](https://github.com/tbruyelle/RxPermissions)、[rxjava2](https://github.com/ReactiveX/RxJava)、[matisse](https://github.com/zhihu/Matisse)、[zxing](https://github.com/zxing/zxing)、[material](https://github.com/rey5137/material)的一套android library。提供了页面导航、数据存储、图片选择、二维码识别/生成、权限等原生功能给weex端调用，节约了自己再去开发这样一套库的时间成本。

不管你是做hybrid开发，还是纯跨平台开发，android端直接依赖此库(业务逻辑交给前端去搞定 罒ω罒)。

### 1.1.1、依赖方式

**源码依赖：**下载例子程序，直接import weexplus module

**远程依赖：**....

建议使用源码依赖的方式，方便业务扩展。
### 1.1.2、配置Application

继承**weexplus**中的**WeexApplication**，或者在你Application的onCreate方法中调用：

```java
	WeexApplication.initialize(this);
```

### 1.2、web视角

WeexPlus可以让你在没有android开发人员的情况下，满足native功能调用的需求。

### 1.2.1、依赖方式
下载例子程序，将 **android** 文件夹整个放入weex目录的 **platforms** 下，不再需要命令weex platform add android生成android程序。

### 1.2.2、配置app
项目集成好后，通过android studio打开android项目，找到app/src/main/res/values/strings.xml，修改app名称和第一个页面入口url的值。
```xml
  
	<!--app名字-->
    <string name="app_name">WeexPlus</string>
    <!-- 入口url 换成你自己的第一个页面文件，可以是本地, 也可以是网络 -->
    <string name="entrance_url">file://main_demo.js</string>
```

集成好后执行 ```weex run android``` 看效果

## 2、weex调用
导入 **WeexPlus** 的 **AppModule** 组件

```vue
//App原生通信模块
const appModule = weex.requireModule("AppModule");
```


## <span id="xgwd">相关文档</span>

[Weex概念快速上手](https://weex.apache.org/cn/guide/index.html?spm=a2c4g.11186623.2.3.RFPXAb)

[Weex技术手册快览](https://weex.apache.org/cn/references/android-apis.html?spm=a2c4g.11186623.2.4.RFPXAb)

[Weex工程原理](https://weex.apache.org/cn/wiki/?spm=a2c4g.11186623.2.5.RFPXAb)


