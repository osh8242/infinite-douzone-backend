package com.douzone.rest.sadeductpay.controller;

import com.douzone.rest.sadeductpay.service.SaDeductPayService;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sadeductpay")
@CrossOrigin(origins = "http://localhost:3000/")
public class SaDeductPayController {
    private SaDeductPayService saDeductPayService;

    @Autowired
    public SaDeductPayController(SaDeductPayService saDeductPayService) {
        this.saDeductPayService = saDeductPayService;
    }

//    @PostMapping("/getSaDeductPayByCdEmp")
//    public List<SaDeductPay> getSaDeductPayByCdEmp(@RequestBody SaDeductPay saDeductPay) {
//
//        List<SaDeductPay> saDeductPayList = null;
//
//        try {
//            saDeductPayList = saDeductPayService.getSaDeductPayByCdEmp(saDeductPay);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return saDeductPayList;
//    }

    @PutMapping("/updateSaDeductPay")
    public void updateSaDeductPay(@RequestBody Map<String, Object> requestMap) {
        try {
            saDeductPayService.updateSaDeductPay(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

//    @PostMapping("/getSalDeductPaySum")
//    public List<SaDeductPay> getSalDeductPaySum(@RequestBody SaDeductPay saDeductPay) {
//
//        List<SaDeductPay> salDeductPaySum = null;
//        try {
//            salDeductPaySum = saDeductPayService.getSalDeductPaySum(saDeductPay);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return salDeductPaySum;
//    }


}
