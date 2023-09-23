package com.douzone.rest.saallowpay.service;

import com.douzone.rest.sadeductpay.vo.SaDeductPay;

import java.util.ArrayList;
import java.util.List;

public class SaDeductCalculationService {
    private int salaryAllowPay;
    private String dateId;

    private void receiveSalaryAllowPay(String salaryAllowPay){
        this.salaryAllowPay = Integer.parseInt(salaryAllowPay);
    }
    private void receiveDateId(String dateId){
        this.dateId = dateId;
    }


}
