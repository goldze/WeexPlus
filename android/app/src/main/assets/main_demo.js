// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 16);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */,
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.geRootIp = geRootIp;
exports.getUrl = getUrl;
exports.getQueryVariable = getQueryVariable;
var IP_ROOT = "http://192.168.17.108:8082";
function geRootIp() {
    return IP_ROOT;
}
function getUrl(url) {
    return IP_ROOT + url;
}

//解析url的参数
function getQueryVariable(variable) {
    var query = weex.config.bundleUrl;
    var num = query.indexOf('?');
    query = query.substr(num + 1);
    var vars = query.split("&");

    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
        return false;
    }
}

/***/ }),
/* 2 */,
/* 3 */,
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = __webpack_require__(5);

Object.defineProperty(exports, 'default', {
  enumerable: true,
  get: function get() {
    return _interopRequireDefault(_index).default;
  }
});

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/***/ }),
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(6)
)

/* script */
__vue_exports__ = __webpack_require__(7)

/* template */
var __vue_template__ = __webpack_require__(9)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "E:\\weex\\project\\weex-plus\\we\\node_modules\\weex-ui\\packages\\wxc-button\\index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-d9889112"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__


/***/ }),
/* 6 */
/***/ (function(module, exports) {

module.exports = {
  "wxc-btn": {
    "width": "702",
    "height": "88",
    "alignItems": "center",
    "justifyContent": "center",
    "borderRadius": "12",
    "opacity": 1
  },
  "btn-text": {
    "textOverflow": "ellipsis",
    "lines": 1,
    "fontSize": "36",
    "color": "#ffffff"
  }
}

/***/ }),
/* 7 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; }; //
//
//
//
//
//
//
//
//
//
//
//
//

var _type = __webpack_require__(8);

exports.default = {
  props: {
    text: {
      type: String,
      default: '确认'
    },
    size: {
      type: String,
      default: 'none'
    },
    type: {
      type: String,
      default: 'red'
    },
    disabled: {
      type: Boolean,
      default: false
    },
    btnStyle: Object,
    textStyle: Object
  },
  computed: {
    mrBtnStyle: function mrBtnStyle() {
      var type = this.type,
          disabled = this.disabled,
          btnStyle = this.btnStyle,
          size = this.size;


      var mrBtnStyle = _extends({}, _type.STYLE_MAP[type], btnStyle, _type.BUTTON_STYLE_MAP[size]);

      var disableStyle = { opacity: 0.2 };
      if (type === 'white') {
        disableStyle = { backgroundColor: 'rgba(0, 0, 0, 0.1)' };
      }

      return disabled ? _extends({}, mrBtnStyle, disableStyle, {
        borderWidth: 0
      }) : mrBtnStyle;
    },
    mrTextStyle: function mrTextStyle() {
      var type = this.type,
          disabled = this.disabled,
          textStyle = this.textStyle,
          size = this.size;

      var mrTextStyle = _extends({}, _type.TEXT_STYLE_MAP[type], textStyle, _type.TEXT_FONTSIZE_STYLE_MAP[size]);
      return disabled ? _extends({}, mrTextStyle, { color: '#FFFFFF' }) : mrTextStyle;
    }
  },
  methods: {
    onClicked: function onClicked(e) {
      var type = this.type,
          disabled = this.disabled;

      this.$emit('wxcButtonClicked', { e: e, type: type, disabled: disabled });
    }
  }
};

/***/ }),
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
var STYLE_MAP = exports.STYLE_MAP = {
  red: {
    backgroundColor: '#FF5000'
  },
  yellow: {
    backgroundColor: '#FFC900'
  },
  white: {
    backgroundColor: '#FFFFFF',
    borderColor: '#A5A5A5',
    borderWidth: '1px'
  },
  blue: {
    backgroundColor: '#0F8DE8'
  }
};

var TEXT_STYLE_MAP = exports.TEXT_STYLE_MAP = {
  red: {
    color: '#FFFFFF'
  },
  yellow: {
    color: '#FFFFFF'
  },
  blue: {
    color: '#FFFFFF'
  },
  white: {
    color: '#3D3D3D'
  }
};

var BUTTON_STYLE_MAP = exports.BUTTON_STYLE_MAP = {
  full: {
    width: '702px',
    height: '88px'
  },
  big: {
    width: '339px',
    height: '70px'
  },
  medium: {
    width: '218px',
    height: '60px'
  },
  small: {
    width: '157px',
    height: '44px'
  }
};

var TEXT_FONTSIZE_STYLE_MAP = exports.TEXT_FONTSIZE_STYLE_MAP = {
  full: {
    fontSize: '36px'
  },
  big: {
    fontSize: '32px'
  },
  medium: {
    fontSize: '28px'
  },
  small: {
    fontSize: '24px'
  }
};

/***/ }),
/* 9 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["wxc-btn"],
    style: _vm.mrBtnStyle,
    attrs: {
      "accessible": true,
      "ariaLabel": _vm.text
    },
    on: {
      "click": _vm.onClicked
    }
  }, [_c('text', {
    staticClass: ["btn-text"],
    style: _vm.mrTextStyle
  }, [_vm._v(_vm._s(_vm.text))])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),
/* 10 */,
/* 11 */,
/* 12 */,
/* 13 */,
/* 14 */,
/* 15 */,
/* 16 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _main_demo = __webpack_require__(17);

var _main_demo2 = _interopRequireDefault(_main_demo);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

_main_demo2.default.el = '#root';
new Vue(_main_demo2.default);

/***/ }),
/* 17 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(18)
)

/* script */
__vue_exports__ = __webpack_require__(19)

/* template */
var __vue_template__ = __webpack_require__(20)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "E:\\weex\\project\\weex-plus\\we\\src\\main_demo.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-e6534504"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__


/***/ }),
/* 18 */
/***/ (function(module, exports) {

module.exports = {
  "btnStyle": {
    "marginTop": "10"
  },
  "img-wrapper": {
    "marginTop": "10",
    "marginRight": "10",
    "marginBottom": "10",
    "marginLeft": "10",
    "width": "100",
    "height": "100"
  },
  "wrapper": {
    "backgroundColor": "#F5F5DC",
    "justifyContent": "center",
    "alignItems": "center"
  },
  "scroller-img": {
    "height": "100",
    "marginTop": "10",
    "marginRight": "10",
    "marginBottom": "10",
    "marginLeft": "10",
    "backgroundColor": "rgba(203,209,212,0.2)",
    "flexDirection": "row"
  },
  "divMain": {
    "alignItems": "center"
  }
}

/***/ }),
/* 19 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _wxcButton = __webpack_require__(4);

var _wxcButton2 = _interopRequireDefault(_wxcButton);

var _base = __webpack_require__(1);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

//网络请求
var stream = weex.requireModule("stream");
//全局的App原生通信模块
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//导入weex-ui包
var appModule = weex.requireModule("AppModule");
//自定义的Toast模块
var toastModule = weex.requireModule("ToastModule");
//导入base,js
exports.default = {
  components: {
    WxcButton: _wxcButton2.default
  },
  data: {
    imgs: "",
    imgsShow: false,
    bitmap: ""
  },
  methods: {
    //打开新页面
    openPager: function openPager() {
      appModule.event("START_PAGER", {
        url: (0, _base.geRootIp)() + "/dist/index.js",
        title: "新页面",
        data: {}
      }, function (e) {
        toastModule.showShort("打开页面成功!");
      }, function (e) {
        toastModule.showShort("打开页面失败!");
      });
    },
    //关闭新页面
    closePager: function closePager() {
      appModule.event("CLOSE_PAGER", {}, function (e) {
        toastModule.showShort("关闭页面成功!");
      }, function (e) {
        toastModule.showShort(e.error);
      });
    },
    //写入数据
    writeData: function writeData() {
      appModule.event("WRITE_DATA", {
        key: "user_info",
        value: "{'userName':'张三','age':'18岁'}"
      }, function (e) {
        toastModule.showShort("写入成功!");
      });
    },
    //读取数据
    readData: function readData() {
      appModule.event("READ_DATA", {
        key: "user_info"
      }, function (e) {
        toastModule.showShort(e.value);
      });
    },
    //请求获取经纬度
    requestLocation: function requestLocation() {
      appModule.event("REQUEST_LOCATION", {}, function (e) {
        toastModule.showShort(e.lngAndLat);
      }, function (e) {
        toastModule.showShort("定位失败，请检查权限是否打开!");
      });
    },
    //扫描二维码
    scanningQRCode: function scanningQRCode() {
      appModule.event("SCANNING_QR", {}, function (e) {
        toastModule.showShort(e.result);
      }, function (e) {
        toastModule.showShort("扫描失败，请检查权限是否打开!");
      });
    },
    //生成二维码
    produceQRCode: function produceQRCode() {
      var _this = this;
      appModule.event("PRODUCE_QR", {
        body: "https://github.com/goldze/WeexPlus"
      }, function (e) {
        toastModule.showShort("生成成功!");
        _this.bitmap = e.bitmap;
      }, function (e) {
        toastModule.showShort("二维码生成失败!");
      });
    },
    //选择图片
    imageSelect: function imageSelect() {
      var _this = this;
      appModule.event("IMAGE_SELECT", {}, function (e) {
        _this.imgsShow = true;
        _this.imgs = e.imgs;
        toastModule.showShort("选择了" + e.imgs.length + "张照片");
      }, function (e) {
        toastModule.showShort("图片选择失败!");
      });
    },
    //打开天气预报界面
    openWeather: function openWeather() {
      appModule.event("START_PAGER", {
        url: (0, _base.geRootIp)() + "/dist/weather.js",
        title: "NO_NAVIGATION",
        data: {}
      });
    }
  }
};

/***/ }),
/* 20 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["divMain"]
  }, [_c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "打开新界面"
    },
    on: {
      "wxcButtonClicked": _vm.openPager
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "关闭界面"
    },
    on: {
      "wxcButtonClicked": _vm.closePager
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "写入磁盘"
    },
    on: {
      "wxcButtonClicked": _vm.writeData
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "读取磁盘"
    },
    on: {
      "wxcButtonClicked": _vm.readData
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "获取经纬度"
    },
    on: {
      "wxcButtonClicked": _vm.requestLocation
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "扫描二维码"
    },
    on: {
      "wxcButtonClicked": _vm.scanningQRCode
    }
  }), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "选择图片"
    },
    on: {
      "wxcButtonClicked": _vm.imageSelect
    }
  }), (_vm.imgsShow) ? _c('scroller', {
    staticClass: ["scroller-img"],
    attrs: {
      "scrollDirection": "horizontal"
    }
  }, _vm._l((_vm.imgs), function(i) {
    return _c('div', {
      key: i
    }, [_c('image', {
      staticClass: ["img-wrapper"],
      attrs: {
        "src": i
      }
    })])
  })) : _vm._e(), _c('wxc-button', {
    staticClass: ["btnStyle"],
    attrs: {
      "text": "天气预报"
    },
    on: {
      "wxcButtonClicked": _vm.openWeather
    }
  })], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);