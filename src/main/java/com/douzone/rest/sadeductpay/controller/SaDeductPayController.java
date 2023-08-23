package com.douzone.rest.sadeductpay.controller;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.service.SaDeductPayService;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sadeductpay")
@CrossOrigin(origins = "http://localhost:3000/")
public class SaDeductPayController {
    private SaDeductPayService saDeductPayService;

    @Autowired
    public SaDeductPayController(SaDeductPayService saDeductPayService) {
        this.saDeductPayService = saDeductPayService;
    }

    @PostMapping("/getSaDeductPayByCdEmp")
    public List<SaDeductPay> getSaDeductPayByCdEmp(@RequestBody SaDeductPay saDeductPay) {

        List<SaDeductPay> saDeductPayList = null;

        try {
            saDeductPayList = saDeductPayService.getSaDeductPayByCdEmp(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return saDeductPayList;
    }

    @PutMapping("/updateSaDeductPay")
    public void updateSaDeductPay(SaDeductPay saDeductPay) {
        try {
            saDeductPayService.updateSaDeductPay(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
