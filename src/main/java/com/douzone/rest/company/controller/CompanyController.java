package com.douzone.rest.company.controller;

import com.douzone.rest.company.service.CompanyService;
import com.douzone.rest.company.vo.DataSourceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/createNewSchema")
    public String createNewSchema(@RequestBody Map<String, String> object){
        String companyCode = object.get("companyCode");
        String password = object.get("password");
        System.out.println("companyCode = " + companyCode);
        System.out.println("password = " + password);
        try {
            companyService.createNewSchema(companyCode, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "create company successfully";
    }
}
