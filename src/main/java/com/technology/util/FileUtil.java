package com.technology.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件处理工具类
 * @author: huangzhb
 * @Date: 2018年12月04日 09:08:31
 * @Description:
 */
@Slf4j
public class FileUtil {


    /**
     * 获取文件名称(处理浏览器的兼容问题)
     * @param request 请求对象
     * @param fileName 文件名
     * @return 处理后的文件名
     */
    public static String getFileName(HttpServletRequest request, String fileName) {

        try {
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
            } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {

                    fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器

            }else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
