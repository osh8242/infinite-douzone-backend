package com.douzone.rest.company.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingCompanyDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return CompanyContextHolder.getCompanyCode();
    }
}
