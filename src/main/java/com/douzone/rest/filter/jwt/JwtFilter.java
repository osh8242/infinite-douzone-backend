package com.douzone.rest.filter.jwt;

import com.douzone.rest.auth.jwt.JwtService;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JwtFilter.doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        System.out.println("Request URI: " + requestURI);

        // 요청패턴 company 무인증 통과
        if (requestURI.startsWith("/company")) {
            System.out.println("/company 요청 -> 무인증 통과");
            RequestDispatcher dispatcher = request.getRequestDispatcher(requestURI);
            dispatcher.forward(request, response);
            return;
        }
        // 요청패턴이 /auth인 경우 필터를 종료하고 DispatcherSevlet에 넘긴다
        if (requestURI.startsWith("/auth")) {
            System.out.println("/auth 요청 -> 인증절차 시작");
            RequestDispatcher dispatcher = request.getRequestDispatcher(requestURI);
            dispatcher.forward(request, response);
            return;
        }

        System.out.println("httpRequest.getHeader(\"Authorization\") = " + httpRequest.getHeader("Authorization"));
        String token = httpRequest.getHeader("Authorization").trim().split(" ")[1];
        System.out.println("getHeader(Authorization) = " + token);
        if (token != null && jwtService.validateToken(token)) {
            System.out.println("유효한 토큰입니다");
            chain.doFilter(httpRequest, response);
        } else {
            System.out.println("유효하지 않은 토큰입니다");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}