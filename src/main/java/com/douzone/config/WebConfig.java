package com.douzone.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/swsm/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST") // 허용할 HTTP 메서드 설정
                .allowCredentials(true); // 필요한 경우 인증 정보 허용
    }
}