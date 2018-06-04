var IP_ROOT = "http://192.168.17.104:8082";
export function geRootIp() {
    return IP_ROOT;
}
export function getUrl(url) {
    return IP_ROOT + url;
}

//解析url的参数
export function getQueryVariable(variable) {
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