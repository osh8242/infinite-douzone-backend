package com.douzone.rest.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // 시크릿 키 생성. HS512 알고리즘을 위한 키 크기에 맞게 생성
    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // 액세스 토큰 만료 시간 (1시간)
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;

    // 리프레시 토큰 만료 시간 (7일)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;


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
    }
}
