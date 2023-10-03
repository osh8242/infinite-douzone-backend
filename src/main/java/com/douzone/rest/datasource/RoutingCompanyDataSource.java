package com.douzone.rest.datasource;

import com.douzone.rest.company.config.CompanyContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingCompanyDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("RoutingCompanyDataSource.determineCurrentLookupKey");
        System.out.println("CompanyContextHolder.getCompanyCode() = " + CompanyContextHolder.getCompanyCode());
        System.out.println("연결된 companyCode = " + CompanyContextHolder.getCompanyCode());
        return CompanyContextHolder.getCompanyCode();
    }
}
