package com.douzone.rest.company.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final String DATA_SOURCE_URL = "jdbc:log4jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.ap-chuncheon-1.oraclecloud.com))(connect_data=(service_name=gf2a91e2c0ac50a_oshdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    private final String DRIVER_CLASS_NAME = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    private final Map<Object, Object> targetDataSources = new HashMap<>();

    private RoutingCompanyDataSource routingCompanyDataSource;
    @Bean
    public DataSource initDataSource() {
        routingCompanyDataSource = new RoutingCompanyDataSource();

        DataSource company1DataSource = createDataSource("HR", "DouzoneTeam3");
        //DataSource company2DataSource = createDataSource("HR101", "1004");

        targetDataSources.put("HR", company1DataSource);
        //targetDataSources.put("HR101", company2DataSource);

        routingCompanyDataSource.setTargetDataSources(targetDataSources);
        routingCompanyDataSource.setDefaultTargetDataSource(company1DataSource); // default data source

        return routingCompanyDataSource;
    }
    private DataSource createDataSource(String companyCode, String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(DATA_SOURCE_URL);
        hikariConfig.setUsername(companyCode);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

    public void addNewDataSource(String companyCode, String password) {
        DataSource newDataSource = createDataSource(companyCode, password);
        targetDataSources.put(companyCode, newDataSource);
        routingCompanyDataSource.setTargetDataSources(new HashMap<>(targetDataSources));
        routingCompanyDataSource.afterPropertiesSet(); // 데이터소스 변경을 알리기 위해 호출
    }

}