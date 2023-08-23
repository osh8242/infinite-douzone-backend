package com.douzone.rest.saallowpay.controller;

import com.douzone.rest.saallowpay.service.SaAllowPayService;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saallowpay")
@CrossOrigin(origins = "http://localhost:3000/")
public class SaAllowPayController {
    SaAllowPayService saAllowPayService;
    @Autowired
    public SaAllowPayController(SaAllowPayService saAllowPayService){
        this.saAllowPayService = saAllowPayService;
    }

    //사원별 급여항목 리스트
    @PostMapping("/getSaAllowPayByCdEmp")
    public List<SaAllowPay> getSaAlLowPayList(@RequestBody SaAllowPay saAllowPay) {

        List<SaAllowPay> saAllowPayList = null;

        try {
            saAllowPayList = saAllowPayService.getSaAlLowPayList(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return saAllowPayList;
    }
    @PutMapping("/updateSalowPay")
    public void updateSalowPay(SaAllowPay saAllowPay) {
        try {
            saAllowPayService.updateSalowPay(saAllowPay);
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
