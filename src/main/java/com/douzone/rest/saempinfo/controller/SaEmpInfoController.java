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
public class SaEmpInfoController {
    SaEmpInfoService saEmpInfoService;
    @Autowired
    public SaEmpInfoController(SaEmpInfoService saEmpInfoService){
        this.saEmpInfoService = saEmpInfoService;
    }
    
    //전체조회
    @PostMapping("/getAll")
    public Map<String, Object> getAll(@RequestBody Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();

        try {
            result = saEmpInfoService.getAll(requestMap);
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
    @PostMapping("/insertSaEmpInfo")
    public int insertSaEmpInfo(@RequestBody SaEmpInfo saEmpInfo){
        int result = 0;
        try {
            result = saEmpInfoService.insertSaEmpInfo(saEmpInfo);
        }catch (Exception e){
            e.getStackTrace();
        }
        return  result;
    }

    //삭제
    @DeleteMapping("/deleteSaEmpList")
    public int deleteSaEmpList(@RequestBody List<Map<String,String>> deleteEmpList) {
        int result = 0;

        try {
            result = saEmpInfoService.deleteSaEmpList(deleteEmpList);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
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
