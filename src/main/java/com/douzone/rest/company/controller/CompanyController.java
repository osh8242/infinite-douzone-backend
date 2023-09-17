package com.douzone.rest.company.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @PostMapping("/createCompany")
    public String createCompany(@RequestParam("companyCode") String companyCode){

        return "create company successfully";
    }
}
