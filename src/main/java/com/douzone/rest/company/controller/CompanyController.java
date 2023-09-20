package com.douzone.rest.company.controller;

import com.douzone.rest.company.vo.DataSourceInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @PostMapping("/createDataSourceInfo")
    public String createDataSourceInfo(@RequestBody DataSourceInfo dataSourceInfo){

        return "create company successfully";
    }
}
