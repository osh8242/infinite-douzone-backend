package com.douzone.rest.sadeductpay.controller;

import com.douzone.rest.sadeductpay.service.SaDeductPayService;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaDeductPayController {
    private SaDeductPayService saDeductPayService;

    @Autowired
    public SaDeductPayController(SaDeductPayService saDeductPayService) {
        this.saDeductPayService = saDeductPayService;
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
