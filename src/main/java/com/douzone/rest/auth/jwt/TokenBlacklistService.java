//package com.douzone.rest.auth.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//
//@Service
//public class TokenBlacklistService {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    // 토큰 블랙리스트 추가
//    public void addTokenToBlacklist(String token) {
//        // redix 토큰을 키로, true 설정
//        redisTemplate.opsForValue().set(token, true);
//
//        // 추가한 토큰에 만료 시간을 설정 : 1시간
//        redisTemplate.expire(token, Duration.ofHours(1));
//    }
//
//    // 토큰 블랙리스트 존재 검증
//    public boolean isTokenBlacklisted(String token) {
//        // 있 T 없 F
//        return redisTemplate.hasKey(token);
//    }
//}
