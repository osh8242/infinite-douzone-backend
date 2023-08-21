package com.douzone.rest.sallowpay.service;

import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import com.douzone.rest.sallowpay.dao.SaLowPayMapper;
import com.douzone.rest.sallowpay.vo.SaLowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaLowPayService {

    SaLowPayMapper saLowPayMapper;

    @Autowired
    public void SaEmpInfoService(SaLowPayMapper saLowPayMapper) {
        this.saLowPayMapper = saLowPayMapper;
    }

    public List<SaLowPay> getSalLowPayList(SaLowPay saLowPay) {
        return saLowPayMapper.getSalLowPayList(saLowPay);
    }

    public void updateSalowPay(SaLowPay saLowPay) {
        try {
            saLowPayMapper.updateSalowPay(saLowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
