package com.douzone.rest;

import com.douzone.rest.jwt.JwtService;
import com.douzone.rest.jwt.config.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@MapperScan("com.douzone.rest.**.dao")
public class InfiniteDouzoneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfiniteDouzoneBackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(CorsFilter corsFilter) {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(corsFilter);
		registrationBean.setOrder(1);  // CORS 필터가 JWT 필터보다 먼저 실행되도록 순서 설정
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter(JwtService jwtService) {
		System.out.println("FilterConfig.jwtFilter");
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter(jwtService));
		registrationBean.addUrlPatterns("/jwt/validateToken");
		registrationBean.setOrder(2);
		return registrationBean;
	}
}
