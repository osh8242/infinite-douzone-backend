package com.douzone.rest.datasource;

import com.douzone.rest.company.vo.DataSourceVo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.*;
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
        // 데이터소스 목록을 담는 Map
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 디폴트 데이터소스
        DataSource dataSource = createDataSource("HR", PASSWORD);
        targetDataSources.put("default", dataSource);

        // 데이터소스 목록 파일에서 데이터소스 목록을 불러오기
        Map<String, DataSourceVo> dataSourceInfos = loadDataSourceInfos();
        System.out.println("dataSourceInfos = " + dataSourceInfos.toString());
        StringBuilder connections = new StringBuilder();
        if (dataSourceInfos.size() != 0) {
            for (DataSourceVo dataSourceInfo : dataSourceInfos.values()) {
                String companyCode = dataSourceInfo.getCompanyCode();
                try {
                    dataSource = createDataSource(companyCode, dataSourceInfo.getPassword());
                    targetDataSources.put(companyCode, dataSource);
                    connections.append("companyCode = " + companyCode + "(연결성공)").append("\n");
                } catch (Exception e) {
                    System.err.println("해당 회사코드로 데이터소스 연결실패: " + dataSourceInfo.getCompanyCode());
                    e.printStackTrace();
                    targetDataSources.remove(companyCode);
                    connections.append("companyCode = " + companyCode + "(연결실패)").append("\n");
                }
            }
        }
        System.out.println("========CONNECTION STATUS=========\n" + connections);
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

        // 맵을 파일에 저장
        saveDataSourceInfos(companyCode, password);
    }

    // 파일에서 데이터소스 목록 불러오기
    private Map<String, DataSourceVo> loadDataSourceInfos() {
        System.out.println("DataSourceConfig.loadDataSourceInfos");
        Map<String, DataSourceVo> dataSourceInfos = new HashMap<>();
        File file = new File("dataSources.dat");
        if (!file.exists()) {
            return dataSourceInfos; // 파일이 없으면 빈 맵을 반환
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                dataSourceInfos = (Map<String, DataSourceVo>) obj;
            } else {
                throw new ClassNotFoundException("Data does not match the expected type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 데이터소스 목록 출력
            System.out.println("Loaded DataSourceInfos: " + dataSourceInfos);
            return dataSourceInfos;
        }
    }

    // 파일에 데이터 소스 목록을 저장하기
    private void saveDataSourceInfos(String companyCode, String password) {
        System.out.println("DataSourceConfig.saveDataSourceInfos");

        // DataSourceInfo 객체만을 저장할 맵을 생성
        Map<String, DataSourceVo> dataSourceInfoMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
            if (entry.getValue() instanceof DataSourceVo) {
                dataSourceInfoMap.put(companyCode, (DataSourceVo) entry.getValue());
            }
        }
        dataSourceInfoMap.put(companyCode, new DataSourceVo(companyCode, password));
        // 데이터소스 목록 출력
        System.out.println("Saving DataSourceInfos: " + dataSourceInfoMap);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dataSources.dat"))) {
            oos.writeObject(dataSourceInfoMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasDataSource(String companyCode){
        return targetDataSources.get(companyCode) == null ? false : true;
    }


}