package com.technology.lock;

import com.alibaba.fastjson.JSON;
import com.technology.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/28 8:44
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Component
@Aspect
public class BndyCacheAspect {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(com.technology.lock.BndyCache)")
    public Object cacheAroundAdvice(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        // 获取连接点签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取练级诶单的注解信息
        BndyCache bndyCache = signature.getMethod().getAnnotation(BndyCache.class);

        int timeout = bndyCache.timeout();
        int random  = bndyCache.random();

        // 获取缓存的前缀
        String prefix = bndyCache.prefix();
        // 组装成key
        String key = prefix + Arrays.asList(point.getArgs()).toString();
        // 1.查询缓存
        result = this.cacheHit(signature, key);

        if (result != null) {
            return result;
        }

        // 初始化分布式锁
        RLock lock = this.redissonClient.getLock("bndyCache" + Arrays.asList(point.getArgs()).toString());
        // 防止缓存穿透 加锁
        lock.lock();

        // 再次检查内存是否有，因为高并发下，可能在加锁这段时间内，已有其他线程放入缓存
        result = this.cacheHit(signature, key);

        if (result != null) {
            lock.unlock();
            return result;
        }

        // 2.执行查询的业务逻辑从数据库查询
        result = point.proceed(point.getArgs());
        // 并把结果放入缓存
        this.stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(result), timeout + (int)(Math.random() * random), TimeUnit.SECONDS);

        // 释放锁
        lock.unlock();

        return result;
    }

    // 查询缓存的方法
    private Object cacheHit(MethodSignature signature, String key) {
        // 1.查询缓存
        String cache = this.stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(cache)) {
            // 有，则反序列化，直接返回
            // 获取返回方法类型
            Class returnType = signature.getReturnType();
            // 不能使用parseArray<cache, T>，因为不知道List<T>中的泛型
            return JSON.parseObject(cache, returnType);
        }
        return null;
    }

}
