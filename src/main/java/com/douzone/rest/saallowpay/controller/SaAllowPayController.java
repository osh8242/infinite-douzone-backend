package com.douzone.rest.saallowpay.controller;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.saallowpay.service.SaAllowPayService;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
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
    public String mergeSalAllowPay(@RequestBody SaAllowPay saAllowPay) {
        String dateId = "";
        try {
            dateId = saAllowPayService.mergeSalAllowPay(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return dateId;
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
    public int setCopyLastMonthData(@RequestBody SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayService.setCopyLastMonthData(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PostMapping("/insertSalAllow")
    public int insertSalAllow(@RequestBody SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayService.insertSalAllow(saAllowPay);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
    @PutMapping("/updateSalAllow")
    public int updateSalAllow(@RequestBody SaAllowPay saAllowPay){
        int result = 0;
        try {
            System.out.println("here");
            result = saAllowPayService.updateSalAllow(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
    @DeleteMapping("deleteSalAllow")
    public int deleteSalAllow(@RequestBody SaAllowPay saAllowPay){
        int result = 0;
        try {
            result = saAllowPayService.deleteSalAllow(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @PutMapping("updateNonTaxLimit")
    public int updateNonTaxLimit(@RequestBody SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayService.updateNonTaxLimit(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

}
