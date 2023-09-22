package com.douzone.rest.filter;

import com.douzone.rest.filter.datasource.DataSourceFilter;
import com.douzone.rest.filter.jwt.JwtFilter;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.Collection;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(CorsFilter corsFilter) {
        System.out.println("FilterConfig.corsFilterRegistrationBean");
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(corsFilter);
        registrationBean.setOrder(1);  // 필터의 적용순번
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistrationBean(JwtFilter jwtFilter) {
        System.out.println("FilterConfig.jwtFilterRegistrationBean");
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>(jwtFilter);
        registrationBean.addUrlPatterns("/*");
        //registrationBean.addUrlPatterns("/jwt/validateToken");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<DataSourceFilter> dataSourceFilterRegistrationBean(DataSourceFilter dataSourceFilter) {
        System.out.println("FilterConfig.dataSourceFilterRegistrationBean");
        FilterRegistrationBean<DataSourceFilter> registrationBean = new FilterRegistrationBean<>(dataSourceFilter);
        registrationBean.setOrder(3);
        return registrationBean;
    }
}
