//package com.douzone.rest.config;
//
//import com.douzone.rest.auth.jwt.JwtAuthenticationFilter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // CSRF 보호를 비활성화 (API 서버일 경우)
//                .authorizeRequests()
//                .anyRequest().authenticated() // 모든 요청에 대해 인증이 필요
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 유효성 검증 필터 추가
//    }
//}
