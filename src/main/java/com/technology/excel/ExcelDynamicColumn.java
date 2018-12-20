package com.technology.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description: 导出Excel动态列注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ExcelDynamicColumn {

    /***
     * @Description: 动态字段属性名，通过反射获取getDynamicFieldName()得到Map<String,Object>来组装动态列
     * @return
     */
    public String dynamicFieldName();
}
