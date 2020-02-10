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
};

function replaceIllegalNumber(obj) {
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    //只能输入两个小数
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
};

/**
 * 价格格式化允许输入1位小数, 最大值99999
 */
function formatPrice() {
    var obj = $(this);
    obj.val(obj.val().replace(/[^\d.]/g,""));  //清除“数字”和“.”以外的字符
    obj.val(obj.val().replace(/^\./g,"")); //验证第一个字符是数字
    obj.val(obj.val().replace(/\.{2,}/g,".")); //只保留第一个. 清除多余的
    obj.val(obj.val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
    obj.val(obj.val().replace(/^(\d{1,5})\.(\d).*$/,'$1.$2'));//只能输入1个小数
    obj.val(obj.val().replace(/^(\d{5}).*$/,'$1'));// 限制输入的是整数
    if(obj.val().indexOf(".")< 0 && obj.val() !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        obj.val(parseFloat(obj.val()));
    }
}