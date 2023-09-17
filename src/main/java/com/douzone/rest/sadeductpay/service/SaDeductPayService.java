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

//    public int updateSaDeductPay(Map<String, Object> requestMap) {
//        int result = 0;
//
//        List<Map<String, Object>> updateDeductPayList = (ArrayList<Map<String, Object>>) requestMap.get("calData");
//
//        for (Map<String, Object> saDeductPay : updateDeductPayList) {
//            saDeductPay.put("allowMonth", requestMap.get("allowMonth"));
//            saDeductPay.put("cdEmp", requestMap.get("cdEmp"));
//            saDeductPayDao.updateSaDeductPay(saDeductPay);
//        }
//
//        return result;
//    }

    public List<Map<String, String>> getSalDeductPaySum(Map<String, String> requestMap) {
        return saDeductPayDao.getSalDeductPaySum(requestMap);
    }

}
