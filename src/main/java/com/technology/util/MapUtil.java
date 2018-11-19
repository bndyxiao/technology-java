package com.technology.util;

/**
 * 地图工具类
 * @author: huangzhb
 * @Date: 2018年11月19日 09:00:44
 * @Description:
 */
public class MapUtil {


    /**
     * 校验经度
     * @param longitude 经度
     * @return true | false
     */
    public static boolean checkLongitude(String longitude){
        String regLongitude = "((?:[0-9]|[1-9][0-9]|1[0-7][0-9])\\.([0-9]{0,6}))|((?:180)\\.([0]{0,6}))";
        return longitude.matches(regLongitude)? true : false;
    }

    /**
     * 校验纬度
     * @param latitude 纬度
     * @return true | false
     */
    public static boolean checkLatitude(String latitude){
        String regLatitude = "((?:[0-9]|[1-8][0-9])\\.([0-9]{0,6}))|((?:90)\\.([0]{0,6}))";
        return latitude.matches(regLatitude)? true : false;
    }
}
