package com.douzone.rest.saempinfo.controller;


import com.douzone.rest.saempinfo.service.SaEmpInfoService;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/saEmpInfo")
@CrossOrigin(origins = "http://localhost:3000/")
public class SaEmpInfoController {
    SaEmpInfoService saEmpInfoService;
    @Autowired
    public SaEmpInfoController(SaEmpInfoService saEmpInfoService){
        this.saEmpInfoService = saEmpInfoService;
    }
    
    //전체조회
    @PostMapping("/getAll")
    public Map<String, Object> getAll(@RequestBody Map<String, Object> reqestMap) {

        Map<String, Object> result = new HashMap<>();

        try {
            result = saEmpInfoService.getAll(reqestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    //조건조회
//    @PostMapping("/getSaEmpInfoByCdEmp")
//    public SaEmpInfo getSaEmpInfoByCdEmp(@RequestBody SaEmpInfo saEmpInfo) {
//
//        SaEmpInfo getSaEmpInfoByCdEmp = null;
//        try {
//            getSaEmpInfoByCdEmp = saEmpInfoService.getSaEmpInfoByCdEmp(saEmpInfo);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//
//        return getSaEmpInfoByCdEmp;
//    }

    //삽입
//    @PostMapping("/getEmpAddByCdEmp")
//    public ResponseEntity<EmpAdd> getAllEmpAdd(@RequestBody EmpAdd empAdd) {
//        empAdd = empAddService.getEmpAddByCdEmp(empAdd);
//        return ResponseEntity.ok(empAdd);
//    }

    //삭제
    @DeleteMapping("/deleteSaEmpInfo")
    public void deleteSaEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoService.deleteSaEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    //수정
    @PutMapping("/updateSaEmpInfo")
    public void updateEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoService.updateEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }



}
