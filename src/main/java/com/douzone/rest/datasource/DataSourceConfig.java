package com.douzone.rest.datasource;


import com.douzone.rest.company.vo.DataSourceVo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    Map<Object, Object> targetDataSources = new HashMap<>();
    RoutingCompanyDataSource routingCompanyDataSource;

    //application.properties에서 설정
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS_NAME;
    @Value("${spring.datasource.url}")
    private String DATA_SOURCE_URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Bean
    public DataSource initDataSource() {
        routingCompanyDataSource = new RoutingCompanyDataSource();

        // 데이터소스 목록을 담는 Map
        Map<Object, Object> targetDataSources = new HashMap<>();

        // 디폴트(기본) 데이터소스
        DataSource dataSource = createDataSource("ADMIN", PASSWORD);
        targetDataSources.put("default", dataSource);

        routingCompanyDataSource.setTargetDataSources(targetDataSources);
        routingCompanyDataSource.setDefaultTargetDataSource(targetDataSources.get("default")); // default data source

        return routingCompanyDataSource;
    }


    // 데이터소스 객체 생성
    DataSource createDataSource(String companyCode, String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(DATA_SOURCE_URL);
        hikariConfig.setUsername(companyCode);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

    // 데이터소스 목록에 데이터소스 추가
    public void addNewDataSource(String companyCode, String password) {
        System.out.println("DataSourceConfig.addNewDataSource");
        System.out.println("companyCode = " + companyCode);
        System.out.println("password = " + password);
        DataSource newDataSource = createDataSource(companyCode, password);
        targetDataSources.put(companyCode, newDataSource);
        routingCompanyDataSource.setTargetDataSources(new HashMap<>(targetDataSources));
        routingCompanyDataSource.afterPropertiesSet(); // 데이터소스 변경을 알리기 위해 호출
    }

    private Map<String, DataSourceVo> createDataSourceVoMapFromTargetDataSources() {
        Map<String, DataSourceVo> dataSourceVoMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
            HikariDataSource hikariDataSource = (HikariDataSource) entry.getValue();
            DataSourceVo dataSourceVo = new DataSourceVo(hikariDataSource.getUsername(), hikariDataSource.getPassword());
            dataSourceVoMap.put((String) entry.getKey(), dataSourceVo);
        }
        return dataSourceVoMap;
    }

    public boolean hasDataSourceKey(String companyCode) {
        return targetDataSources.get(companyCode) != null;
    }

}