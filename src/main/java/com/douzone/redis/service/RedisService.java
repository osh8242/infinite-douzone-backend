package com.douzone.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        System.out.println("RedisService.RedisService");
        this.redisTemplate = redisTemplate;
        setValue("RedisService", System.currentTimeMillis());
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println("key = " + key);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
