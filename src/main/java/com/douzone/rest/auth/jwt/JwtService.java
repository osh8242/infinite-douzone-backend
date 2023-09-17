package com.douzone.rest.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // 시크릿 키 생성. HS512 알고리즘을 위한 키 크기에 맞게 생성
    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // 액세스 토큰 만료 시간 (1시간)
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;

    // 리프레시 토큰 만료 시간 (7일)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    // 액세스 토큰 생성
    public String generateAccessToken(String userId) {
        return generateToken(userId, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    //리프레시 토큰 생성
    public String generateRefreshToken(String userId) {
        return generateToken(userId, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    // JWT 토큰 생성
    private String generateToken(String userId, long expirationTime) {
        return Jwts.builder()
                .setSubject(userId)  // 토큰 주체 설정 (여기서는 사용자 ID)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // HS512 알고리즘과 시크릿 키로 서명 설정
                .compact();  // 토큰 문자열로 변환
    }

    // 액세스 토큰 생성
    public String generateToken(String userId) {
        return generateToken(userId, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    //시크릿 키 반환
    public byte[] getSecretKey() {
        return SECRET_KEY;
    }
}
