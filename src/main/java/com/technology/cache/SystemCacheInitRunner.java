package com.technology.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统缓存初始化
 * @author: huangzhb
 * @Date: 2018年12月19日 16:28:12
 * @Description:
 */
@Slf4j
@Component
@Order(2)
public class SystemCacheInitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("=================> start 初始化系统缓存....");


        log.info("=================> end 初始化系统缓存");
    }
}
