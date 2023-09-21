package com.douzone.rest.filter.jwt;

import com.douzone.rest.auth.jwt.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
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

        String requestURI = httpRequest.getRequestURI();
        System.out.println("Request URI: " + requestURI);

        // 요청패턴이 /auth인 경우 필터를 종료하고 DispatcherSevlet에 넘긴다
        if(requestURI.startsWith("/auth")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(requestURI);
            dispatcher.forward(request, response);
            return;
        }

        String token = httpRequest.getHeader("Authorization").trim().split(" ")[1];
        System.out.println("getHeader(Authorization) = " + token);
        if (token != null && jwtService.validateToken(token, httpRequest)) {
            System.out.println("유효한 토큰입니다");
            chain.doFilter(httpRequest, response);
        } else {
            System.out.println("유효하지 않은 토큰입니다");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}