package com.douzone.rest.company.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final String DATA_SOURCE_URL = "jdbc:log4jdbc:oracle:thin:@localhost:1521:oracle";
    private final String DRIVER_CLASS_NAME = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    private final Map<Object, Object> targetDataSources = new HashMap<>();
    @Bean
    public DataSource initDataSource() {
        RoutingCompanyDataSource companyRoutingDataSource = new RoutingCompanyDataSource();

        DataSource company1DataSource = createDataSource(DATA_SOURCE_URL, "HR", "1004");
        DataSource company2DataSource = createDataSource(DATA_SOURCE_URL, "HR101", "1004");

        targetDataSources.put("COMPANY1", company1DataSource);
        targetDataSources.put("COMPANY2", company2DataSource);

        companyRoutingDataSource.setTargetDataSources(targetDataSources);
        companyRoutingDataSource.setDefaultTargetDataSource(company1DataSource); // default data source

        return companyRoutingDataSource;
    }
    private DataSource createDataSource(String url, String username, String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

}