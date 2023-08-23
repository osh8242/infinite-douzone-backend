package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaAllowPayService {

    SaAllowPayMapper saAllowPayMapper;

    @Autowired
    public void SaEmpInfoService(SaAllowPayMapper saAllowPayMapper) {
        this.saAllowPayMapper = saAllowPayMapper;
    }

    public List<SaAllowPay> getSaAlLowPayList(SaAllowPay saAllowPay) {
        return saAllowPayMapper.getSaAlLowPayList(saAllowPay);
    }

    public void updateSalowPay(SaAllowPay saAllowPay) {
        try {
            saAllowPayMapper.updateSalowPay(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
