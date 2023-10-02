package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaAllowPayService {

    private final SaAllowPayMapper saAllowPayMapper;
    private final SaDeductPayDao saDeductPayDao;
    private final SaEmpInfoMapper saEmpInfoMapper;
    private final SaAllowCalculationService saAllowCalculationService;
    private final SaDeductCalculationService saDeductCalculationService;

    @Autowired
    public SaAllowPayService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductPayDao saDeductPayDao,
            SaEmpInfoMapper saEmpInfoMapper,
            SaAllowCalculationService saAllowCalculationService,
            SaDeductCalculationService saDeductCalculationService
    ) {
        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductPayDao = saDeductPayDao;
        this.saEmpInfoMapper = saEmpInfoMapper;
        this.saAllowCalculationService = saAllowCalculationService;
        this.saDeductCalculationService = saDeductCalculationService;
    }

    public Map<String, Object> getSalaryAllInfoByDate(Map<String, String> requestMap) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            Map<String, String> dateInfo = saEmpInfoMapper.getDateInfo(requestMap);
            resultMap.put("dateInfo", dateInfo);
            resultMap.put("plist", saEmpInfoMapper.getSaEmpInfoList(requestMap));

            Map<String, Object> totalSalPaydata = new HashMap<>();
            totalSalPaydata.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap)); //지급항목
            totalSalPaydata.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap)); //공제항목
            resultMap.put("totalSalPaydata",totalSalPaydata);

        }catch (Exception e){
            e.getStackTrace();
        }

        return resultMap;
    }

    public Map<String, Object> getSaPayByCdEmp(Map<String, String> requestMap) {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            resultMap.put("saAllowPayList", saAllowPayMapper.getSalAlLowPayListByEmp(requestMap));      // 급여항목 리스트
            resultMap.put("sumAllowPayByYnTax", saAllowPayMapper.getSumAllowPayByYnTax(requestMap));    // 과세 비과세 합

            resultMap.put("saDeductPayList", saDeductPayDao.getSaDeductPayByCdEmp(requestMap));         // 공제항목 리스트
            resultMap.put("saEmpDetail", saEmpInfoMapper.getSaEmpInfoByCdEmp(requestMap));              // 사원 상세 정보

//            Map<String, List<Map<String, String>>> totalSalPaydata = new HashMap<>();
//            totalSalPaydata.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap));            // 지급항목
//            totalSalPaydata.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap));            // 공제항목
//
//            resultMap.put("totalSalPaydata", totalSalPaydata);

        } catch (Exception e) {
            e.getStackTrace();
        }

        return resultMap;
    }

    /* 급여항목 입력 or 수정 */
    public String mergeSalAllowPay(SaAllowPay saAllowPay) {
        String dateId = saAllowPay.getDateId();

        try {
            if("".equals(saAllowPay.getDateId())) { // dateId 없는 경우 dateId 생성
                makeDateId(saAllowPay);
            }

            int mergeSalaryAllowPayResult = saAllowCalculationService.mergeNewSalaryAllowPay(saAllowPay); // 급여항목 insert or update

            if(mergeSalaryAllowPayResult > 0) {
                saDeductCalculationService.mergeNewDeductAllowPay(saAllowPay); // 공제항목 insert or update
                dateId = saAllowPay.getDateId();
            }

        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("mergeSalAllowPay에서 터짐");
        }
        return dateId;
    }

    public Map<String, Object> getSalTotalPaySum(Map<String, String> requestMap) {
        Map<String, Object> result = new HashMap<>();
        try {

            result.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap)); //지급항목
            result.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap)); //공제항목

        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public List<Map<String, String>> getPaymentDateList(Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getPaymentDateList(map);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public List<Map<String, String>> getsalAllowList(Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getsalAllowList(map);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public List<Map<String, String>> getNonTaxSalAllowList(Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = saAllowPayMapper.getNonTaxSalAllowList(map);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    public int recalculation(Map<String, Object> requestMap) {
        int result = 0;
        try {
            result = saAllowCalculationService.getReCalculateResult(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    public int updateDate(Map<String, String> requestMap) {
        int result = 0;
        try {
            result = saAllowPayMapper.updateDate(requestMap);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    public int setCopyLastMonthData(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            saAllowPayMapper.makeOneMonthLaterDateId(saAllowPay); // dateId 만들어주기
            result = saAllowPayMapper.setCopyLastMonthData(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    // DateId 만들기
    // Date테이블 dateId 생성하는 프로시져 호출
    // SaAllowPay (allowyear, allowMonth, paymentDate)
    private String makeDateId(SaAllowPay saAllowPay){
        String newDateId = "";

        try {
            saAllowPayMapper.makeDateId(saAllowPay);
            newDateId = saAllowPay.getDateId();
            System.out.println("newDateId");

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("setDateId에서 터짐.");
        }
        return newDateId;
    }


    public int insertSalAllow(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.insertSalAllow(saAllowPay);
            if("N".equals(saAllowPay.getYnTax())){
                saAllowPay.setYnTax("Y");
                result = saAllowPayMapper.insertSalAllow(saAllowPay);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return result;
    }

    public int updateSalAllow(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.updateSalAllow(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public int deleteSalAllow(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.deleteSalAllow(saAllowPay);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }


    public int updateNonTaxLimit(SaAllowPay saAllowPay){
        int result = 0;
        try {
            result = saAllowPayMapper.updateNonTaxLimit(saAllowPay);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

}
