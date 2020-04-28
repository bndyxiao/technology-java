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
}
