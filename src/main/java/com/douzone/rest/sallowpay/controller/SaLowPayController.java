package com.douzone.rest.sallowpay.controller;

import com.douzone.rest.saEmpInfo.service.SaEmpInfoService;
import com.douzone.rest.sallowpay.service.SaLowPayService;
import com.douzone.rest.sallowpay.vo.SaLowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaLowPayController {
    SaLowPayService saLowPayService;
    @Autowired
    public SaLowPayController(SaLowPayService saLowPayService){
        this.saLowPayService = saLowPayService;
    }

    //사원별 급여항목 리스트
    @GetMapping("/getSaLowPayByCdEmp")
    public List<SaLowPay> getSalLowPayList(SaLowPay saLowPay) {

        List<SaLowPay> salLowPayList = null;
        try {
            salLowPayList = saLowPayService.getSalLowPayList(saLowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return salLowPayList;
    }
    @PutMapping("/updateSalowPay")
    public void updateSalowPay(SaLowPay saLowPay) {
        try {
            saLowPayService.updateSalowPay(saLowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
//
//
//    //삭제
//    @DeleteMapping("/deleteEmpInfo")
//    public void deleteEmpInfo(SaLowPay saLowPay) {
//        try {
//            saLowPayService.deleteSaLowPay(saLowPay);
//
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }
//
//    @PutMapping("/updateEmpInfo")
//    public void updateEmpInfo(SaLowPay saLowPay) {
//        try {
//            saLowPayService.updateEmpInfo(saLowPay);
//
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }



}
