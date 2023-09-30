package com.douzone.rest.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // 시크릿 키 생성. HS512 알고리즘을 위한 키 크기에 맞게 생성
    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // 액세스 토큰 만료 시간 (1시간)
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;

    // 리프레시 토큰 만료 시간 (7일)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    // 토큰생성
    public String generateAccessToken(String userId, String companyCode) {
        String token = Jwts.builder()
                .setSubject(userId)
//              .setSubject(companyCode + "&" + userId)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        // Redis에 토큰 값 저장
//        redisTemplate.opsForValue().set(token, userId, ACCESS_TOKEN_EXPIRATION_TIME, TimeUnit.MILLISECONDS);

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);

            // Redis에서 토큰 값 검사
//            if (redisTemplate.hasKey(token)) {
//                return true;
//            }

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


    //// redis 적용 코드
//        private final RedisTemplate<String, String> redisTemplate;
//
//        @Autowired
//        public JwtService(RedisTemplate<String, String> redisTemplate) {
//            this.redisTemplate = redisTemplate;
//        }
//
//        // 액세스 토큰 생성
//        public String generateAccessToken(String companyCode, String userId) {
//            return generateAccessToken(companyCode, userId, ACCESS_TOKEN_EXPIRATION_TIME);
//        }
//
//        // 토큰생성
//        public String generateAccessToken(String userId, String companyCode, long expirationTime) {
//            String subject = "companyCode" + "&" + userId; // 임시 설정 중
//            String token = Jwts.builder()
//                    .setSubject(subject)  // 토큰 주체 설정 (id 별 토큰 발급을 위한)
//                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간 설정
//                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // HS256 알고리즘과 시크릿 키로 서명 설정
//                    .compact();  // 토큰 문자열로 변환
//            redisTemplate.opsForValue().set(subject, token, expirationTime, TimeUnit.MILLISECONDS);
//            return token;
//        }
//
//        //companyCode, userId 반환 (UserVo 객체형태 반환)
//        public UserVo getUserVoFromToken(String token) {
//            UserVo user = null;
//            try {
//                System.out.println("JwtService.getUsernameFromToken");
//                String subject = Jwts.parser()
//                        .setSigningKey(SECRET_KEY)
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .getSubject();
//                String companyCode = subject.split("&")[0];
//                String userId = subject.split("&")[1];
//                user = new UserVo();
//                user.setCompanyCode(companyCode);
//                user.setUserId(userId);
//            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
//                     IllegalArgumentException e) {
//                throw new RuntimeException(e);
//            }
//            return user;
//        }
//    public boolean validateToken(String token, HttpServletRequest request) {
//        try {
//            // JWT 내부 유효성 검사
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//
//            // JWT에서 사용자 정보 가져오기
//            UserVo user = getUserVoFromToken(token);
//            String subject = user.getCompanyCode() + "&" + user.getUserId();
//
//            // Redis에서 사용자 정보 검사
//            if (Boolean.TRUE.equals(redisTemplate.hasKey(subject))) {
//                request.setAttribute("companyCode", user.getCompanyCode());
//                return true;
//            }
//            return false;
//
//        } catch (ExpiredJwtException e) {
//            logger.error("Token has expired: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Token structure is invalid: {}", e.getMessage());
//        } catch (SignatureException e) {
//            logger.error("Token signature is invalid: {}", e.getMessage());
//        } catch (Exception e) {
//            logger.error("Error validating token: {}", e.getMessage());
//        }
//        return false;
//    }

}
