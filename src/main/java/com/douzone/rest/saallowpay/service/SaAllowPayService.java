package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllow;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

            if("BONUS".equals(requestMap.get("salDivision"))){ requestMap.put("ynBonus","true"); }
            if("SAL".equals(requestMap.get("salDivision"))){ requestMap.put("ynSal","true"); }
            resultMap.put("saDeductPayList", saDeductPayDao.getSaDeductPayByCdEmp(requestMap));         // 공제항목 리스트
            resultMap.put("saEmpDetail", saEmpInfoMapper.getSaEmpInfoByCdEmp(requestMap));              // 사원 상세 정보

        } catch (Exception e) {
            e.getStackTrace();
        }

        return resultMap;
    }

    @Transactional
    /* 급여항목 입력 or 수정 */
    public String mergeSalAllowPay(SaAllowPay saAllowPay) {
        String dateId = saAllowPay.getDateId();

        try {
            if("".equals(saAllowPay.getDateId())) { // dateId 없는 경우 dateId 생성
                makeDateId(saAllowPay);
            }

            if("".equals(saAllowPay.getAllowPay())){
                if (saAllowPayMapper.deleteSalAllowPay(saAllowPay) > 0) {
                    saAllowPay.setAllowPay("0");
                    saDeductCalculationService.mergeNewDeductAllowPay(saAllowPay); // 급여항목 insert or update
                }
            } else {
                int mergeSalaryAllowPayResult = saAllowCalculationService.mergeNewSalaryAllowPay(saAllowPay); // 급여항목 insert or update
                if (mergeSalaryAllowPayResult > 0) {
                    saDeductCalculationService.mergeNewDeductAllowPay(saAllowPay); // 공제항목 insert or update
                    dateId = saAllowPay.getDateId();
                }
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

    @Transactional
    public int setCopyLastMonthData(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            // 전월 dateId 불러오기
            List<SaAllowPay> getDateListByLastMonth = saAllowPayMapper.getDateListByLastMonth(saAllowPay);

            // 이번달 월 모든 급여항목 공제항목 삭제
            saAllowPayMapper.deleteSalAllowPayThisMonth(saAllowPay);
            //saAllowPayMapper.deleteSalDeductPayThisMonth(saAllowPay);

            // date별로 for문 돌려서 이번달 dateId 만들고 지난달 급여항목이랑 공제항목데이터 불러오기
            for (SaAllowPay dateLastMonth : getDateListByLastMonth){

                dateLastMonth.setSalDivision(saAllowPay.getSalDivision());

                List<SaAllowPay> getSaAllowPayListByLastMonthPaymentDate = saAllowPayMapper.getSaAllowPayListByLastMonthPaymentDate(dateLastMonth);

                if(getSaAllowPayListByLastMonthPaymentDate.size() > 0){
                    saAllowPay.setPaymentDate(dateLastMonth.getPaymentDate());
                    String newDateId = saAllowPayMapper.getDateId(saAllowPay);

                    if(newDateId==null){
                        saAllowPayMapper.makeDateIdForCopyLastMonth(saAllowPay);
                        newDateId = saAllowPay.getDateId() ;
                    }

                    for (SaAllowPay SaAllowPayByLastMonth : getSaAllowPayListByLastMonthPaymentDate){
                        SaAllowPayByLastMonth.setDateId(newDateId);
                    }

                   result += saAllowPayMapper.copyLastMonthData(getSaAllowPayListByLastMonthPaymentDate);
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("전월데이터 복사에서 터짐");
            throw new RuntimeException("전월데이터 복사에서 예외 발생", e);
        }
        return result;
    }

    // DateId 만들기
    // SaAllowPay (allowyear, allowMonth, paymentDate)
    private String makeDateId(SaAllowPay saAllowPay){
        String newDateId = "";

        try {
            saAllowPayMapper.makeDateId(saAllowPay);
            newDateId = saAllowPay.getDateId();

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("setDateId에서 터짐.");
        }
        return newDateId;
    }

    @Transactional
    public int insertSalAllow(SaAllow saAllow) {
        int result = 0;
        try {
            saAllow.setCdAllow(saAllowPayMapper.createSallowSeq());

            result = saAllowPayMapper.insertSalAllow(saAllow);
            if("N".equals(saAllow.getYnTax())){
                saAllow.setYnTax("Y");
                result = saAllowPayMapper.insertSalAllow(saAllow);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return result;
    }
    @Transactional
    public int updateSalAllow(SaAllow saAllow) {
        int result = 0;
        try {

            SaAllow oriSallow = saAllowPayMapper.getSalAllow(saAllow);

            if(!oriSallow.getYnTax().equals(saAllow.getYnTax())){

                saAllow.setOriginYnTax(oriSallow.getYnTax());

                if ("Y".equals(saAllow.getYnTax())) {
                    result = saAllowPayMapper.deleteSalAllow(saAllow);
                    saAllow.setNonTaxLimit("");
                }

                saAllowPayMapper.updateSalAllow(saAllow);

                if ("N".equals(saAllow.getYnTax())) {
                    saAllow.setYnTax("Y");
                    result = saAllowPayMapper.insertSalAllow(saAllow);
                }

            }else {
                saAllow.setOriginYnTax(saAllow.getYnTax());
                saAllowPayMapper.updateSalAllow(saAllow);
            }
            result = saAllowPayMapper.updateSalAllow(saAllow);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    @Transactional
    public int deleteSalAllow(SaAllow saAllow) {
        int result = 0;
        try {

            // 만약 지급내역이 있으면 ynUse 업데이트 지급내역이 없으면 진짜 삭제
            int count = saAllowPayMapper.selectCountByAllow(saAllow);


            if(count > 0){   // 지급내역이 있다
                saAllow.setYnUse("N");  // 사용여부 'N'

                saAllow.setOriginYnTax(saAllow.getYnTax());
                result = saAllowPayMapper.updateSalAllow(saAllow);

                if("N".equals(saAllow.getYnTax())){
                    saAllow.setOriginYnTax("Y");
                    saAllow.setYnTax("Y");
                    saAllowPayMapper.updateSalAllow(saAllow);
                }

            }else{  //지급내역이 없다
                result = saAllowPayMapper.deleteSalAllow(saAllow);
            }

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

//    private int insertSalAllowPay(SaAllowPay saAllowPay){
//        int result = 0;
//        try {
//            saAllowPayMapper.insertSalAllowPay(saAllowPay);
//        }catch (Exception e){
//            e.getStackTrace();
//            System.out.println("insertSalAllowPay에서 터짐.");
//        }
//        return result;
//    }
//
//    private int updateSalAllowPay(SaAllowPay saAllowPay){
//        int result = 0;
//        try {
//            saAllowPayMapper.updateSalAllowPay(saAllowPay);
//        }catch (Exception e){
//            e.getStackTrace();
//            System.out.println("updateSalAllowPay 터짐.");
//        }
//        return result;
//    }


}
