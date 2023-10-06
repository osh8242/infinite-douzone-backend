package com.douzone.rest.auth.jwt;

import com.douzone.rest.auth.vo.UserVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.douzone.rest.auth.jwt.JwtProperties.*;

@Service
public class JwtService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // 시크릿 키 생성.
     @Value("${jwt.secret}")
     private String SECRET_KEY;

    // 토큰생성
    public String generateAccessToken(String userId, String companyCode, String clientIp) {
        String token = Jwts.builder()
                .setSubject(companyCode + "&" + userId+"&"+clientIp)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        // Redis에 토큰 값 저장
        redisTemplate.opsForValue().set(token, userId, ACCESS_TOKEN_EXPIRATION_TIME, TimeUnit.MILLISECONDS);

        return token;
    }

    // 토큰 검증
    public boolean validateToken(String token, String clientIp) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
           //  Redis에서 토큰 값 검사
            if (Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                if(parseToken(token).get("clientIp").equals(clientIp))
                return true;
            }
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
    }

    //companyCode, userId 반환 (UserVo 객체형태 반환)
    public Map<String, String> parseToken(String token) {
        Map<String, String> tokenBody = null;
        try {
            System.out.println("JwtService.getUsernameFromToken");
            String subject = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            String companyCode = subject.split("&")[0];
            String userId = subject.split("&")[1];
            String clientIp = subject.split("&")[2];

            tokenBody = new HashMap<>();
            tokenBody.put("companyCode",companyCode);
            tokenBody.put("userId",userId);
            tokenBody.put("clientIp",clientIp);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return tokenBody;
    }
}
