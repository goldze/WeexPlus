# WeexPlus

为解决目前移动开发频繁的迭代、开发周期长、人员成本高的问题，移动跨平台开发方案层出不穷。**WeexPlus则是一款基于阿里[weex](http://weex.apache.org/cn/)跨平台方案(android/ios/h5)开发的weex端与android native交互的组件扩展库，提供页面导航、数据存储、图片选择、定位等原生功能。**web开发者不用再为调用native功能而烦恼，android开发者轻松搞定hybrid开发。

## 框架流程

## 框架特点
- **快速集成**

	不管是web开发者，还是android开发者，都可以快速的集成此库。

- **事件模式**

	整体使用事件派发思想，weex与native完全解耦，相互不依赖。weex端指定唯一的Action(动作)，native端会根据Action做出判断，执行对应的逻辑，响应结果。

- **加载器**

	WeexPlus提供一个原生的页面加载器，只需要传入JSBundle文件路径(本地/网络都可以)，会自动渲染页面，并带有material design加载效果。加载失败显示错误信息，可点击重新加载。