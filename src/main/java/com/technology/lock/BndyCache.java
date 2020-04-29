package com.technology.lock;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BndyCache {

    /**
     * 缓存key的前缀
     * @return
     */
    String prefix() default "cache";

    /**
     * 过期时间秒
     */
    int timeout() default 3600;

    /**
     * 随机数种子,防止缓存雪崩
     */
    int random() default 100;
}