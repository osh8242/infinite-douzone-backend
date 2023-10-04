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
        DataSource newDataSource = createDataSource(companyCode, password);
        targetDataSources.put(companyCode, newDataSource);
        routingCompanyDataSource.setTargetDataSources(new HashMap<>(targetDataSources));
        routingCompanyDataSource.afterPropertiesSet(); // 데이터소스 변경을 알리기 위해 호출

        // 맵을 파일에 저장
        saveDataSourceMap(companyCode, password);
    }

    // 파일에서 데이터소스 목록 불러오기
    private Map<String, DataSourceVo> loadDataSourceInfos() {
        System.out.println("DataSourceConfig.loadDataSourceInfos");
        Map<String, DataSourceVo> dataSourceVoMap = new HashMap<>();
        File file = new File("dataSources.dat");
        if (!file.exists()) {
            return dataSourceVoMap; // 파일이 없으면 빈 맵을 반환
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                dataSourceVoMap = (Map<String, DataSourceVo>) obj;
            } else {
                throw new ClassNotFoundException("Data does not match the expected type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 데이터소스 목록 출력
            System.out.println("Loaded DataSourceInfos: " + dataSourceVoMap);
            return dataSourceVoMap;
        }
    }

    // 파일에 데이터 소스 목록을 저장하기
    private void saveDataSourceMap(String companyCode, String password) {
        System.out.println("DataSourceConfig.saveDataSourceInfos");

        // DataSourceVo 객체만을 저장할 맵을 생성
        Map<String, DataSourceVo> dataSourceVoMap = createDataSourceVoMapFromTargetDataSources();
        if (companyCode != null && password != null)
            dataSourceVoMap.put(companyCode, new DataSourceVo(companyCode, password));

        // 데이터소스 목록 출력
        System.out.println("Saving DataSourceInfos: " + dataSourceVoMap);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dataSources.dat"))) {
            oos.writeObject(dataSourceVoMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveDataSourceMap() {
        saveDataSourceMap(null, null);
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
        return targetDataSources.get(companyCode) == null ? false : true;
    }


}