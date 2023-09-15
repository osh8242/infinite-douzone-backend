package com.douzone.rest;

import com.douzone.rest.jwt.JwtService;
import com.douzone.rest.jwt.config.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@MapperScan("com.douzone.rest.**.dao")
public class InfiniteDouzoneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfiniteDouzoneBackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter(JwtService jwtService) {
		System.out.println("FilterConfig.jwtFilter");
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter(jwtService));
		registrationBean.addUrlPatterns("/jwt/validateToken");  // 보호할 URL 패턴 지정
		return registrationBean;
	}
}
