package com.douzone.rest.sadeductpay.controller;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.service.SaDeductPayService;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sadeductpay")
public class SaDeductPayController {
    private SaDeductPayService saDeductPayService;

    @Autowired
    public SaDeductPayController(SaDeductPayService saDeductPayService) {
        this.saDeductPayService = saDeductPayService;
    }

    @GetMapping("/getsalDeductList")
    public List<Map<String, String>> getsalDeductList(@RequestParam Map<String, String> reqestMap) {

        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saDeductPayService.getsalDeductList(reqestMap);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/mergeSalDeductPay")
    public int mergeSalDeductPay(@RequestBody SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayService.mergeSalDeductPay(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/insertSaDeduct")
    public int insertSaDeduct(@RequestBody SaDeductPay saDeductPay) {
        int result = 0;
        try {
            System.out.println("옴?");
            result = saDeductPayService.insertSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PutMapping("/updateSaDeduct")
    public int updateSaDeduct(@RequestBody SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayService.updateSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @DeleteMapping("/deleteSaDeduct")
    public int deleteSaDeduct(@RequestBody SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayService.deleteSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

}
