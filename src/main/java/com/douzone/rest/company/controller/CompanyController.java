package com.douzone.rest.company.controller;

import com.douzone.rest.company.service.CompanyService;
import com.douzone.rest.datasource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @PostMapping("/createNewSchema")
    public String createNewSchema(@RequestBody Map<String, String> object){
        String companyCode = object.get("companyCode");
        String password = object.get("password");
        System.out.println("companyCode = " + companyCode);
        System.out.println("password = " + password);
        try {
            companyService.createNewSchema(companyCode, password);
            dataSourceConfig.addNewDataSource(companyCode, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "create company successfully";
    }
}
