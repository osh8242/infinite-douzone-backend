package com.douzone.rest.saempinfo.controller;

import com.douzone.rest.saempinfo.service.SaEmpInfoService;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaEmpInfoController {
    SaEmpInfoService saEmpInfoService;
    @Autowired
    public SaEmpInfoController(SaEmpInfoService saEmpInfoService){
        this.saEmpInfoService = saEmpInfoService;
    }
    
    //전체조회
    @GetMapping("/getAllSaEmpInfo")
    public List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo) {

        List<SaEmpInfo> getAllEmpInfo = null;
        try {
            getAllEmpInfo = saEmpInfoService.getSaEmpInfoList(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return getAllEmpInfo;
    }

    //조건조회
    @GetMapping("/getSaEmpInfoByCdEmp")
    public SaEmpInfo getSaEmpInfoByCdEmp(SaEmpInfo saEmpInfo) {

        SaEmpInfo getSaEmpInfoByCdEmp = null;
        try {
            getSaEmpInfoByCdEmp = saEmpInfoService.getSaEmpInfoByCdEmp(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return getSaEmpInfoByCdEmp;
    }


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
