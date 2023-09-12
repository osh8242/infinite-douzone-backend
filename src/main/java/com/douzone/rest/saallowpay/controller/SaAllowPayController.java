package com.douzone.rest.saallowpay.controller;

import com.douzone.rest.saallowpay.service.SaAllowPayService;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.service.SaDeductPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/insertSalAllowPay")
    public int insertSalAllowPay(@RequestBody SaAllowPay saAllowPay){
        int result = 0;
        try {
            result = saAllowPayService.insertSalAllowPay(saAllowPay);
        }catch (Exception e){
            e.getStackTrace();
        }
        return  result;
    }

    @PutMapping("/updateSalPay")
    public int updateSalPay(@RequestBody Map<String, Object> requestMap) {
        int result = 0;
        try {
            result = saAllowPayService.updateSalPay(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    @PostMapping("/getSaPayByCdEmp")
    public Map<String, Object> getSaPayByCdEmp(@RequestBody Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();
        try{
            result = saAllowPayService.getSaPayByCdEmp(requestMap);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/getSalTotalPaySum")
    public Map<String, Object> getSalTotalPaySum(@RequestBody Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();
        try {
            result = saAllowPayService.getSalTotalPaySum(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return result;
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
