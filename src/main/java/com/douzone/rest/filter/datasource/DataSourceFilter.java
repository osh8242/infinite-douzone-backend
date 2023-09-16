package com.douzone.rest.filter.datasource;

import com.douzone.rest.company.config.CompanyContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataSourceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("DataSourceFilter.doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String company = httpRequest.getParameter("company");
        System.out.println("company = " + company);

        try {
            if (company != null && (company.equals("COMPANY1") || company.equals("COMPANY2"))) {
                CompanyContextHolder.setCompanyCode(company);
            }
            chain.doFilter(request, response);
        } finally {
            CompanyContextHolder.clear(); // 요청 처리 후 데이터 소스 키를 클리어
        }
    }
}
