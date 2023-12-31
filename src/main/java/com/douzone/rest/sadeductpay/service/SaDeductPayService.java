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
        if("BONUS".equals(requestMap.get("salDivision"))){ requestMap.put("ynBonus" , "Y"); }
        if("SAL".equals(requestMap.get("salDivision"))){ requestMap.put("ynSal" , "Y"); }
        return saDeductPayDao.getSaDeductPayByCdEmp(requestMap);
    }

    public List<Map<String, String>> getSalDeductPaySum(Map<String, String> requestMap) {
        return saDeductPayDao.getSalDeductPaySum(requestMap);
    }

    public List<Map<String, String>> getsalDeductList(Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saDeductPayDao.getsalDeductList(map);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public int mergeSalDeductPay(SaDeductPay saDeductPay) {
        System.out.println("SaDeductPayService.mergeSalDeductPay");
        System.out.println("saDeductPay = " + saDeductPay);
        int result = 0;
        try {
//            result = saDeductPayDao.mergeSalDeductPay(saDeductPay);

            if (saDeductPayDao.getCountSaDeductPay(saDeductPay) == 0) {
                result = saDeductPayDao.insertSaDeductPay(saDeductPay);
            } else {
                result = saDeductPayDao.updateSaDeductPay(saDeductPay);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public int insertSaDeduct(SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayDao.insertSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public int updateSaDeduct(SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayDao.updateSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public int deleteSaDeduct(SaDeductPay saDeductPay) {
        int result = 0;
        try {
            result = saDeductPayDao.deleteSaDeduct(saDeductPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
}
