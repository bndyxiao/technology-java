package com.technology.exception;

import lombok.Data;

/**
 * @author: huangzhb
 * @Date: 2018年12月05日 09:28:01
 * @Description:
 */
@Data
public class ExceptionResult {

    /** 状态码 */
    private int status;
    /** 错误信息 */
    private String message;
    /** 时间戳 */
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
