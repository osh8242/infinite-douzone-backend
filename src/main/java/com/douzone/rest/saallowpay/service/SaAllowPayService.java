package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaAllowPayService {

    private SaAllowPayMapper saAllowPayMapper;
    private SaDeductPayDao saDeductPayDao;
    private SaEmpInfoMapper saEmpInfoMapper;

    @Autowired
    public SaAllowPayService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductPayDao saDeductPayDao,
            SaEmpInfoMapper saEmpInfoMapper
    ) {
        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductPayDao = saDeductPayDao;
        this.saEmpInfoMapper = saEmpInfoMapper;
    }


    public Map<String, Object> getSaPayByCdEmp(Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();

        try {
            result.put("saAllowPayList", saAllowPayMapper.getSalAlLowPayListByEmp(requestMap));  // 급여항목 리스트
            result.put("saDeductPayList", saDeductPayDao.getSaDeductPayByCdEmp(requestMap));    // 공제항목 리스트
            result.put("saEmpDetail", saEmpInfoMapper.getSaEmpInfoByCdEmp(requestMap));         // 사원 상세 정보

            Map<String, List<Map<String, String>>> totalSalPaydata = new HashMap<>();
            totalSalPaydata.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap)); //지급항목
            totalSalPaydata.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap)); //공제항목

            result.put("totalSalPaydata",totalSalPaydata);

        }catch (Exception e){
            e.getStackTrace();
        }

        return result;
    }

    public int mergeSalAllowPay(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.mergeSalAllowPay(saAllowPay);

        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public int updateSalPay(Map<String, Object> requestMap) {
        int result = 0;
        try {
            Map<String, String> updateAllowData = (Map<String, String>)requestMap.get("updateAllowData");
            saAllowPayMapper.updateSalAllowPay(updateAllowData); // 지급항목 수정

            List<Map<String, Object>> updateDeductPayList = (ArrayList<Map<String, Object>>)requestMap.get("updateDeductData");
            for (Map<String, Object> saDeductPay : updateDeductPayList) {
                saDeductPay.put("dateId", requestMap.get("dateId"));
                saDeductPay.put("cdEmp", requestMap.get("cdEmp"));
                saDeductPayDao.updateSaDeductPay(saDeductPay);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public Map<String, Object> getSalTotalPaySum(Map<String, String> requestMap) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap)); //지급항목
            result.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap)); //공제항목

        }catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public List<Map<String, String>> getPaymentDateList(Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getPaymentDateList(map);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }


    public List<Map<String, String>> getsalAllowList(Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getsalAllowList(map);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public List<Map<String, String>> getNonTaxSalAllowList(Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getNonTaxSalAllowList(map);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }
}
