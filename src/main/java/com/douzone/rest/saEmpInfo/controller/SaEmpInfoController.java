package com.douzone.rest.saEmpInfo.controller;

import com.douzone.rest.saEmpInfo.service.SaEmpInfoService;
import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaEmpInfoController {

    SaEmpInfoService saEmpInfoService;
    @Autowired
    public SaEmpInfoController(SaEmpInfoService saEmpInfoService){
        this.saEmpInfoService = saEmpInfoService;
    }

    @GetMapping("/getAllSaEmpInfo")
    public List<SaEmpInfo> getAllSaEmpInfo() {

        List<SaEmpInfo> getAllEmpInfo = null;
        try {
            getAllEmpInfo = saEmpInfoService.getAllSaEmpInfo();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return getAllEmpInfo;
    }

    @DeleteMapping("/deleteEmpInfo")
    public void deleteEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoService.deleteEmpInfo(saEmpInfo);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
