package com.douzone.rest.datasource;

import com.douzone.rest.company.vo.DataSourceInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final String DATA_SOURCE_URL = "jdbc:log4jdbc:oracle:thin:@localhost:1521:oracle";
    //private final String DATA_SOURCE_URL = "jdbc:log4jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.ap-chuncheon-1.oraclecloud.com))(connect_data=(service_name=gf2a91e2c0ac50a_oshdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    private final String DRIVER_CLASS_NAME = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    private final String PASSWORD = "1004";
    //    private final String PASSWORD = "DouzoneTeam3";
    private final Map<Object, Object> targetDataSources = new HashMap<>();

    private RoutingCompanyDataSource routingCompanyDataSource;

    @Bean
    public DataSource initDataSource() {
        routingCompanyDataSource = new RoutingCompanyDataSource();
        //데이터소스 목록을 담는 Map
        Map<Object, Object> targetDataSources = new HashMap<>();
        //디폴트 데이터소스
        DataSource dataSource = createDataSource("HR", PASSWORD);
        targetDataSources.put("default", dataSource);

//        데이터소스 목록 파일에서 데이터소스 목록을 불러오기
//        Map<String, DataSourceInfo> dataSourceInfos = loadDataSourceInfos();
//        System.out.println("dataSourceInfos = " + dataSourceInfos.toString());
//        for (DataSourceInfo dataSourceInfo : dataSourceInfos.values()) {
//            dataSource = createDataSource(dataSourceInfo.getCompanyCode(), dataSourceInfo.getPassword());
//            targetDataSources.put(dataSourceInfo.getCompanyCode(), dataSource);
//        }

        routingCompanyDataSource.setTargetDataSources(targetDataSources);
        routingCompanyDataSource.setDefaultTargetDataSource(targetDataSources.get("default")); // default data source

        return routingCompanyDataSource;
    }

    // 데이터소스 객체 생성
    private DataSource createDataSource(String companyCode, String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(DATA_SOURCE_URL);
        hikariConfig.setUsername(companyCode);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

    // 데이터소스 목록에 데이터소스 추가
    // 데이터소스 목록에 데이터소스 추가
    public void addNewDataSource(String companyCode, String password) {
        DataSource newDataSource = createDataSource(companyCode, password);
        targetDataSources.put(companyCode, newDataSource);
        routingCompanyDataSource.setTargetDataSources(new HashMap<>(targetDataSources));
        routingCompanyDataSource.afterPropertiesSet(); // 데이터소스 변경을 알리기 위해 호출

        // 데이터소스 정보 맵 불러오기
        Map<String, DataSourceInfo> dataSourceInfos = loadDataSourceInfos();

        // 새 DataSourceInfo 객체 생성 및 맵에 추가
        DataSourceInfo newDataSourceInfo = new DataSourceInfo(companyCode, password);
        dataSourceInfos.put(companyCode, newDataSourceInfo);

        // 맵을 파일에 저장
        saveDataSourceInfos(dataSourceInfos);
    }

    // 파일에서 데이터소스 목록 불러오기
    private Map<String, DataSourceInfo> loadDataSourceInfos() {
        System.out.println("DataSourceConfig.loadDataSourceInfos");
        Map<String, DataSourceInfo> dataSourceInfos = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dataSources.dat"))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                dataSourceInfos = (Map<String, DataSourceInfo>) obj;
            } else {
                throw new ClassNotFoundException("Data does not match the expected type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 데이터소스 목록 출력
        System.out.println("Loaded DataSourceInfos: " + dataSourceInfos);
        return dataSourceInfos;
    }

    // 파일에 데이터 소스 목록을 저장하기
    private void saveDataSourceInfos(Map<String, DataSourceInfo> dataSourceInfos) {
        System.out.println("DataSourceConfig.saveDataSourceInfos");
        // 데이터소스 목록 출력
        System.out.println("Saving DataSourceInfos: " + dataSourceInfos);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dataSources.dat"))) {
            oos.writeObject(dataSourceInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}