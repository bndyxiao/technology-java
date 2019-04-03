package com.technology.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: huangzhb
 * @Date: 2019年03月18日 09:54:33
 * @Description:
 */
@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    @GetMapping("test")
    public ResponseEntity<String> test() {

        // 设置一个key，aaa商品的库存数量为100
        stringRedisTemplate.opsForValue().set("aaa", "100");
        return ResponseEntity.ok("操作成功");
    }

    @GetMapping("testDistributed")
    public ResponseEntity<String> testDistributed() {

        // 执行的业务逻辑代码
        RLock lock = redisson.getLock("testRedisson");
        lock.lock(60, TimeUnit.SECONDS);

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("aaa"));
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("aaa", (stock - 1) + "");
                System.out.println("test2_:lockkey:aaa,stock:" + (stock - 1));
            } else {
                System.out.println("很遗憾，您没有抢到");
            }
        } finally {
            lock.unlock();
        }

        return ResponseEntity.ok("操作成功");
    }
}
