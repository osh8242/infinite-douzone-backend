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

    public List<SaAllowPay> getSalAlLowPayList(SaAllowPay saAllowPay) {
        return saAllowPayMapper.getSalAlLowPayList(saAllowPay);
    }

    public int insertSalAllowPay(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.insertSalAllowPay(saAllowPay);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public int updateSalAllowPay(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.updateSalAllowPay(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public List<SaAllowPay> getSalAllowPaySum(SaAllowPay saAllowPay) {
        return saAllowPayMapper.getSalAllowPaySum(saAllowPay);
    }
}
