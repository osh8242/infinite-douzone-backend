package com.douzone.rest.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // ID 별 토큰 생성
    public String generateToken(String userId) {
        long expirationTime = 1000 * 60 * 60; // 1시간

        // JWT 토큰 발급
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰의 만료 시간
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 토큰 서명 사용 키 설정
                .compact();  // 토큰 문자열 반환
    }
}
