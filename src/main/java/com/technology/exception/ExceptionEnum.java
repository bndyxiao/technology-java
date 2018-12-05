package com.technology.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: huangzhb
 * @Date: 2018年12月05日 09:23:12
 * @Description:
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    BAD_REQUEST(400, "调用不合法"),
    PAGE_NO_FOUND(404, "页面未找到"),
    BRAND_NO_FOUND(404, "品牌列表未找到"),
    INTERNAL_ERROR(500, "服务器内部错误");

    /** 状态码 */
    private int code;
    /** 错误信息 */
    private String msg;

}
