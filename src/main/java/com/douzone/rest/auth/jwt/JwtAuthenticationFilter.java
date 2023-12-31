package com.douzone.rest.auth.jwt;

import com.douzone.rest.auth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

import static com.douzone.rest.auth.jwt.JwtProperties.HEADER_STRING;
import static com.douzone.rest.auth.jwt.JwtProperties.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

//    @Autowired
//    private TokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtService jwtService){
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

//        String token = header.substring(TOKEN_PREFIX.length());
//
//        if (tokenBlacklistService.isTokenBlacklisted(token)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }

        Authentication authentication = getAuthentication(request);
        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            Map<String, String> tokenBody = jwtService.parseToken(token.replace(TOKEN_PREFIX, ""));
            request.setAttribute("companyCode", tokenBody.get("companyCode"));
            if (tokenBody != null) {
                return new UsernamePasswordAuthenticationToken(tokenBody.get("userId"), null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
