package com.douzone.rest.jwt.config;

import com.douzone.rest.jwt.JwtProperties;
import com.douzone.rest.jwt.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class JwtFilter implements Filter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JwtFilter.doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("Authorization").trim().split(" ")[1];
        System.out.println("getHeader(Authorization) = " + token);
        if (token != null && jwtService.validateToken(token)) {
            System.out.println("유효한 토큰입니다");
            chain.doFilter(request, response);
        } else {
            System.out.println("유효하지 않은 토큰입니다");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}