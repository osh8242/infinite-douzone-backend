package com.douzone.rest.auth.jwt;

import java.util.Base64;

public interface JwtProperties {
    int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60; // 토큰 유효시간 (1시간)
    int REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 일주일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
