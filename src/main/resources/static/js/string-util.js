//校验经度是否符合规范
//校验经度
function checkLongitude(value){
    var longrg = /^(\-|\+)?(((\d|[1-9]\d|1[0-7]\d|0{1,3})\.\d{0,6})|(\d|[1-9]\d|1[0-7]\d|0{1,3})|180\.0{0,6}|180)$/;
    if(!longrg.test(value)){
        return '经度整数部分为0-180,小数部分为0到6位!';
    }
    return true;
};

//校验纬度是否符合规范
//纬度
function checkLatitude(value){
    var latreg = /^(\-|\+)?([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
    if(!latreg.test(value)){
        return '纬度整数部分为0-90,小数部分为0到6位!';
    }
    return true;
};

/**
 * 过滤文件名的特殊字符
 * @param s
 * @returns {string}
 */
function stripFileName(fileName) {
    // var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&;—|{}【】‘；：”“'。，、？]")
    var pattern = new RegExp(/'|#|&|\\|\/|:|\?|"|<|>|\*|\|/g);
    fileName = fileName.replaceAll(pattern, "");

    return fileName;
}