package com.douzone.rest.filter;

import com.douzone.rest.filter.datasource.DataSourceFilter;
import com.douzone.rest.filter.jwt.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

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
        registrationBean.setOrder(0);
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
