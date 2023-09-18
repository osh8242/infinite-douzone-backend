package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllow;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaAllowCalculationService {
    private final SaAllowPayMapper saAllowPayMapper;

    @Autowired
    public SaAllowCalculationService(SaAllowPayMapper saAllowPayMapper) {
        this.saAllowPayMapper = saAllowPayMapper;
    }

    public List<SaAllowPay> newSalaryAllowPayList(SaAllowPay saAllowPay) {
        List<SaAllowPay> salaryAllowList = new ArrayList<>();
        SaAllow salAllowInfo = getSalAllowInfo(saAllowPay);

        try {
            if ("N".equals(saAllowPay.getYnTax())) {    // 비과세 항목인지 판단
                deleteSalAllowPay(saAllowPay);             // 삭제가 완료되면
                int limit = Integer.parseInt(salAllowInfo.getNonTaxLimit());

                int allowPay = Integer.parseInt(saAllowPay.getAllowPay());

                if (allowPay > limit) {
                    salaryAllowList.add(createSaAllowPay(saAllowPay, "N", String.valueOf(limit)));
                    salaryAllowList.add(createSaAllowPay(saAllowPay, "Y", String.valueOf(allowPay - limit)));
                } else {
                    salaryAllowList.add(createSaAllowPay(saAllowPay, "N", String.valueOf(allowPay)));
                }

            } else { // 과세 항목
                salaryAllowList.add(createSaAllowPay(saAllowPay, "Y", saAllowPay.getAllowPay()));
            }
        }catch (Exception e){
            e.getStackTrace();
        }

        return salaryAllowList;
    }

    private SaAllowPay createSaAllowPay(SaAllowPay saAllowPay, String ynTax, String allowPay) {
        SaAllowPay newSaAllowPay = new SaAllowPay();
        newSaAllowPay.setCdAllow(saAllowPay.getCdAllow());
        newSaAllowPay.setCdEmp(saAllowPay.getCdEmp());
        newSaAllowPay.setDateId(saAllowPay.getDateId());
        newSaAllowPay.setYnTax(ynTax);
        newSaAllowPay.setAllowPay(allowPay);

        return newSaAllowPay;
    }

    private SaAllow getSalAllowInfo(SaAllowPay saAllowPay) {
        try {
            return saAllowPayMapper.getSalAllowInfo(saAllowPay);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean deleteSalAllowPay(SaAllowPay saAllowPay) {
        return saAllowPayMapper.deleteSalAllowPay(saAllowPay) > 0;
    }
}
