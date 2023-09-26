package com.douzone.rest.sadeductpay.controller;

import com.douzone.rest.sadeductpay.service.SaDeductPayService;
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

}
