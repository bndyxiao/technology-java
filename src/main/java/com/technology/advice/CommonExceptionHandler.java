package com.technology.advice;

import com.technology.exception.CommonException;
import com.technology.exception.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * 自定义异常：需要用到@ControllerAdvice修饰类和@ExceptionHandler(RuntimeException.class|Exception.class|...)进行修饰异常方法
 * 通用异常处理类
 * @author: huangzhb
 * @Date: 2018年12月05日 09:09:51
 * @Description:
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handleException(CommonException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
