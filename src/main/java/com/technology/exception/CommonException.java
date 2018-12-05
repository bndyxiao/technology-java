package com.technology.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author: huangzhb
 * @Date: 2018年12月05日 09:21:59
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
