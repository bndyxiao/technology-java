
/**
 * 是否安卓系统
 */
function isAndroidOS() {
    var u = navigator.userAgent;
    // android终端
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;
    return isAndroid;
}

/**
 * 是否苹果系统
 * @returns {boolean}
 */
function isMacOS() {
    var u = navigator.userAgent;
    //ios终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
    return isiOS;
}