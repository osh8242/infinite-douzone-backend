package com.douzone.rest.datasource.controller;

import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.DataSourceConfig;
import com.douzone.rest.datasource.service.DataSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    private final DataSourceConfig dataSourceConfig;
    private final DataSourceService dataSourceService;

    public DataSourceController(DataSourceConfig dataSourceConfig, DataSourceService dataSourceService) {
        this.dataSourceConfig = dataSourceConfig;
        this.dataSourceService = dataSourceService;
    }

    @PostMapping("/insertDataSource")
    public String insertDataSource(@RequestBody DataSourceVo dataSourceVo) {
        System.out.println("DataSourceController.insertDataSource");

        String companyCode = dataSourceVo.getCompanyCode();
        String password = dataSourceVo.getPassword();
        try {
            dataSourceConfig.addNewDataSource(companyCode, password);
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }
        return "insertDataSource 요청성공";
    }

    @GetMapping("/getAllDataSourceVo")
    public ResponseEntity<List<DataSourceVo>> getAllDataSourceVo() {
        List<DataSourceVo> result = null;
        try {
            result = dataSourceService.getAllDataSourceVo();
        } catch (Exception e) {
            System.out.println("에러발생 " + e);
        }
        return null;
    }

    @DeleteMapping("/deleteDataSource")
    public int deleteDataSource(@RequestBody DataSourceVo dataSourceVo) {
        System.out.println("DataSourceController.deleteDataSource");

        int result = 0;

        return result;
    }

}
