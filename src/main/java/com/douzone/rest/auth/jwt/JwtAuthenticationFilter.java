package com.douzone.rest.auth.jwt;

import com.douzone.rest.auth.vo.UserVo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.douzone.rest.auth.jwt.JwtProperties.*;
import static com.douzone.rest.auth.jwt.JwtProperties.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService){
        System.out.println("jwt service");
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getAuthentication(request);

        // ??
        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            UserVo userVo = jwtService.parseToken(token.replace(TOKEN_PREFIX, ""));
            request.setAttribute("companyCode", userVo.getCompanyCode());
            if (userVo != null) {
                return new UsernamePasswordAuthenticationToken(userVo.getUserId(), null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
