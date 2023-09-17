package com.douzone.rest.jwt;

import java.util.Base64;

public interface JwtProperties {
    int EXPIRATION_TIME = 1000 * 60 * 60; // 토큰 유효시간 (1시간)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
