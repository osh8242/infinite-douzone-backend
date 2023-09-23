package com.douzone.rest.saallowpay.controller;

import com.douzone.rest.saallowpay.service.SaAllowPayService;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sallowpay")
public class SaAllowPayController {
    SaAllowPayService saAllowPayService;

    @Autowired
    public SaAllowPayController(SaAllowPayService saAllowPayService) {
        this.saAllowPayService = saAllowPayService;
    }


    @PostMapping("/getSalaryAllInfoByDate")
    public Map<String, Object> getAll(@RequestBody Map<String, String> requestMap) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = saAllowPayService.getSalaryAllInfoByDate(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    //사원별 급여항목 리스트
    @PostMapping("/mergeSalAllowPay")
    public int mergeSalAllowPay(@RequestBody SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayService.mergeSalAllowPay(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    @PostMapping("/getSaPayByCdEmp")
    public Map<String, Object> getSaPayByCdEmp(@RequestBody Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();
        try {
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
            System.out.println(requestMap.toString());
            result = saAllowPayService.getSalTotalPaySum(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return result;
    }

    @GetMapping("/getPaymentDateList")
    public List<Map<String, String>> getPaymentDateList(@RequestParam Map<String, String> reqestMap) {

        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayService.getPaymentDateList(reqestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @GetMapping("/getsalAllowList")
    public List<Map<String, String>> getsalAllowList(@RequestParam Map<String, String> reqestMap) {

        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayService.getsalAllowList(reqestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @GetMapping("/getNonTaxSalAllowList")
    public List<Map<String, String>> getNonTaxSalAllowList(@RequestParam Map<String, String> reqestMap) {

        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayService.getNonTaxSalAllowList(reqestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/recalculation")
    public int recalculation(@RequestBody Map<String, Object> requestMap) {
        int result = 0;

        try {
            result = saAllowPayService.recalculation(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    @PostMapping("/updateDate")
    public int updateDate(@RequestBody Map<String, String> requestMap) {
        int result = 0;
        try {
            result = saAllowPayService.updateDate(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/setCopyLastMonthData")
    public int setCopyLastMonthData(@RequestBody Map<String, String> requestMap) {
        int result = 0;
        try {
            result = saAllowPayService.setCopyLastMonthData(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
}
