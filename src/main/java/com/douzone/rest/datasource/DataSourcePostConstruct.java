package com.douzone.rest.datasource;

import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.service.DataSourceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourcePostConstruct {
    private final DataSourceConfig dataSourceConfig;
    private final DataSourceService dataSourceService;

    @Autowired
    public DataSourcePostConstruct(DataSourceConfig dataSourceConfig, DataSourceService dataSourceService) {
        this.dataSourceConfig = dataSourceConfig;
        this.dataSourceService = dataSourceService;
    }

    @PostConstruct
    public void addDataSourcesFromDB() {
        System.out.println("DataSourceConfig.addDataSourcesFromDB");
        //DB로부터 모든 데이터소스 목록을 불러옴
        List<DataSourceVo> dataSourceInfos = dataSourceService.getAllDataSourceVo();
        StringBuilder stringBuilder = new StringBuilder();

        //데이터소스 목록이 비어있지 않다면 현재 백엔드 서버에 연결작업하기
        if (!dataSourceInfos.isEmpty()) {
            List<DataSourceVo> failedDataSourceList = new ArrayList<>();
            for (DataSourceVo dataSourceVo : dataSourceInfos) {
                String companyCode = dataSourceVo.getCompanyCode();
                try {
                    DataSource dataSource = dataSourceConfig.createDataSource(companyCode, dataSourceVo.getPassword());
                    dataSourceConfig.targetDataSources.put(companyCode, dataSource);
                    stringBuilder.append("companyCode = ").append(companyCode).append("(연결성공)").append("\n");
                } catch (Exception e) {
                    failedDataSourceList.add(dataSourceVo);
                    stringBuilder.append("companyCode = ").append(companyCode).append("(연결실패)").append("\n");
                }
            }
            for (DataSourceVo dataSourceVo : failedDataSourceList) {
                System.out.println("연결에 실패한 소스 : " + dataSourceVo);
                dataSourceVo.setStatus(0);
                dataSourceService.updateDataSourceVo(dataSourceVo);
            }
            //데이터 소스 연결상태 출력
            stringBuilder.append("========CONNECTION STATUS=========\n");
            System.out.println(stringBuilder);

            //백엔드 서버의 데이터소스목록 업데이트
            dataSourceConfig.routingCompanyDataSource.setTargetDataSources(dataSourceConfig.targetDataSources);
            dataSourceConfig.routingCompanyDataSource.afterPropertiesSet();
        } else {
            System.out.println("추가할 데이터소스 목록이 없습니다.");
        }
    }

}
