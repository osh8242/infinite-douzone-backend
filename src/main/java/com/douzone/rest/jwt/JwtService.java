package com.douzone.rest.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class JwtService {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public JwtService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String createToken(String username, long expirationTime) {
        System.out.println("JwtService.createToken");
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SECRET_KEY)
                .compact();
        redisTemplate.opsForValue().set(username, token, expirationTime, TimeUnit.MILLISECONDS);
        System.out.println("username = " + username);
        System.out.println("token = " + token);
        System.out.println("-----------------------------");
        return token;
    }

    public String getUsernameFromToken(String token) {
        System.out.println("JwtService.getUsernameFromToken");
        System.out.println("token = " + token);
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean deleteToken(String username) {
        return Boolean.TRUE.equals(redisTemplate.delete(username));
    }

    public boolean validateToken(String token) {
        System.out.println("JwtService.validateToken");
        String username = getUsernameFromToken(token);
        System.out.println("username = " + username);
        return Boolean.TRUE.equals(redisTemplate.hasKey(username));
    }
}
