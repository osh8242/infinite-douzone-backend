package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
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
    private SaAllowCalculationService saAllowCalculationService;
    private SaDeductCalculationService saDeductCalculationService;

    @Autowired
    public SaAllowPayService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductPayDao saDeductPayDao,
            SaEmpInfoMapper saEmpInfoMapper,
            SaAllowCalculationService saAllowCalculationService
    ) {
        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductPayDao = saDeductPayDao;
        this.saEmpInfoMapper = saEmpInfoMapper;
        this.saAllowCalculationService = saAllowCalculationService;
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
    /* 급여항목 입력 or 수정 */
    public int mergeSalAllowPay(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            List<SaAllowPay> newSalaryAllowPayList = saAllowCalculationService.newSalaryAllowPayList(saAllowPay);
            System.out.println(newSalaryAllowPayList.toString());
            result = saAllowPayMapper.mergeSalAllowPay(newSalaryAllowPayList);

            //List<SaDeductPay> salartyDeductPayList = saDeductCalculationService.salaryDeductPayList(saAllowPay.getAllowPay(),saAllowPay.getDateId());
            //result = saDeductPayDao.updateSaDeductPayList(salartyDeductPayList);

        }catch (Exception e){
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
