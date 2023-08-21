package com.douzone.rest.saDeductPay.controller;

import com.douzone.rest.empadd.service.EmpAddService;
import com.douzone.rest.saDeductPay.service.SaDeductPayService;
import com.douzone.rest.saDeductPay.vo.SaDeductPay;
import com.douzone.rest.sallowpay.vo.SaLowPay;
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
