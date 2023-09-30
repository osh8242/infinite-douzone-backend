package com.douzone.rest.sadeductpay.service;

import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SaDeductPayService {

    private SaDeductPayDao saDeductPayDao;
    @Autowired
    public SaDeductPayService(SaDeductPayDao saDeductPayDao) {
        this.saDeductPayDao = saDeductPayDao;
    }

    public List<SaDeductPay> getSaDeductPayByCdEmp(Map<String, String> requestMap) {
        return saDeductPayDao.getSaDeductPayByCdEmp(requestMap);
    }

    public List<Map<String, String>> getSalDeductPaySum(Map<String, String> requestMap) {
        return saDeductPayDao.getSalDeductPaySum(requestMap);
    }

    public List<Map<String, String>> getsalDeductList(Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saDeductPayDao.getsalDeductList(map);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }
}
