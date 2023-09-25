package com.douzone.rest.datasource.controller;

import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.DataSourceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datasource")
public class DataSourceController {
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @PostMapping("/insertDataSource")
    public String insertDataSource(@RequestBody DataSourceVo dataSourceVo){
        String companyCode = dataSourceVo.getCompanyCode();
        String password = dataSourceVo.getPassword();
        try {
            dataSourceConfig.addNewDataSource(companyCode, password);
        } catch (Exception e) {
            System.out.println("에러발생 : " + e);
        }
        return "insertDataSource 요청성공";
    }
}
