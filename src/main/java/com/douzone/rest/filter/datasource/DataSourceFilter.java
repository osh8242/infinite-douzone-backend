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
        String companyCode = (String) httpRequest.getAttribute("companyCode");
        System.out.println("companyCode = " + companyCode);

        try {
            if (companyCode != null) {
                System.out.println("setCompanyCode = " + companyCode);
                CompanyContextHolder.setCompanyCode(companyCode);
            }
            chain.doFilter(request, response);
        } finally {
            System.out.println("ClearCompanyContextHolder");
            CompanyContextHolder.clear(); // 요청 처리 후 데이터 소스 키를 클리어
        }
    }
}
