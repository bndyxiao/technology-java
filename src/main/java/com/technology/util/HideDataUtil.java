package com.technology.util;

/**
 * @author: huangzhb
 * @Date: 2019年03月04日 14:22:15
 * @Description:
 */
public class HideDataUtil {

    /**
     * 方法描述 隐藏银行卡号中间的字符串（使用*号），显示前四后四
     * @param idcard
     * @param prefix 前缀展示位数
     * @param suffix 后缀展示位数
     * @return
     */
    public static String hideIdCard(String idcard, int prefix, int suffix) {
        if(StringUtils.isBlank(idcard)) {
            return idcard;
        }

        int length = idcard.length();

        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++) {
            if(i < prefix || i >= (length - suffix)) {
                sb.append(idcard.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }

    /**
     * 方法描述 隐藏手机号中间位置字符，显示前三后三个字符
     * @param telephone 手机号码
     * @param prefix 前缀展示字符个数
     * @param suffix 后缀展示字符个数
     * @return
     */
    public static String hideTelephone(String telephone, int prefix, int suffix) {
        if(StringUtils.isBlank(telephone)) {
            return telephone;
        }

        int length = telephone.length();

        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++) {
            if(i < prefix || i >= (length - suffix)) {
                sb.append(telephone.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }
}
