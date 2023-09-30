package com.douzone.rest.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.douzone.rest.auth.vo.UserVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.douzone.rest.auth.jwt.JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME;
import static com.douzone.rest.auth.jwt.JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
public class JwtService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public JwtService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // 시크릿 키 생성. HS512 알고리즘을 위한 키 크기에 맞게 생성
    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // 시크릿 키 생성. HS256 알고리즘을 위한 키 크기에 맞게 생성
//    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // 액세스 토큰 생성
    public String generateAccessToken(String companyCode, String userId) {

        return generateToken(companyCode, userId, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    //리프레시 토큰 생성
    public String generateRefreshToken(String companyCode, String userId) {
        return generateToken(companyCode, userId, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    // JWT 토큰 생성
    private String generateToken(String companyCode, String userId, long expirationTime) {
        String subject = companyCode + "&" + userId;
        String token = Jwts.builder()
                .setSubject(subject)  // 토큰 주체 설정 (id 별 토큰 발급을 위한)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // HS256 알고리즘과 시크릿 키로 서명 설정
                .compact();  // 토큰 문자열로 변환
        redisTemplate.opsForValue().set(subject, token, expirationTime, TimeUnit.MILLISECONDS);
        return token;

    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token has expired: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token structure is invalid: {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("Token signature is invalid: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error validating token: {}", e.getMessage());
        }
        return false;
    // 액세스 토큰 생성
    public String generateToken(String companyCode, String userId) {
        return generateToken(companyCode, userId, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    //companyCode, userId 반환 (UserVo 객체형태 반환)
    public UserVo getUserVoFromToken(String token) {
        UserVo user = null;
        try {
            System.out.println("JwtService.getUsernameFromToken");
            String subject = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            String companyCode = subject.split("&")[0];
            String userId = subject.split("&")[1];
            user = new UserVo();
            user.setCompanyCode(companyCode);
            user.setUserId(userId);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            System.out.println("JwtService.validateToken");
            UserVo user = getUserVoFromToken(token);
            System.out.println("user = " + user);
            String subject = user.getCompanyCode() + "&" + user.getUserId();
            if (Boolean.TRUE.equals(redisTemplate.hasKey(subject))) {
                request.setAttribute("companyCode", user.getCompanyCode());
                return true;
            } else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String parseToken(String token) {
        if (validateToken(token)) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
        return null;
    //시크릿 키 반환
    public String getSecretKey() {
        return SECRET_KEY;
    }
}
