package com.technology.util;

import java.math.BigDecimal;

/**
 * 金额元分之间转换工具类
 * @author : huangzhb
 * @date: 2018年12月12日 16:50:26
 */
public class AmountUtil {

    /**金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";


    /**
     * 钱数转成元
     * 举例：money2YuanOfDecimal(100, 2, BigDecimal.ROUND_DOWN) => 1.00
     * @param money
     * @param scale
     * @param model
     *
     * @return 转换后的字符串
     */
    public static BigDecimal money2YuanOfDecimal(Object money, int scale, int model){
        if(money == null){
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(money.toString());
        return bigDecimal.divide(new BigDecimal(100)).setScale(scale, model);
    }
}
