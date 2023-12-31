package com.douzone.rest.saallowpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllow;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaAllowPayMapper {
    List<SaAllowPay> getSalAlLowPayListByEmp(Map<String, String> requestMap);   // 급여항목 지급내역 리스트 조회
    List<SaAllowPay> getSalAlLowPayListByEmpForCalculation(Map<String, String> requestMap);   // 급여항목 지급내역 리스트 조회
    List<Map<String, String>> getSalAllowPaySum(Map<String, String> requestMap);    // select_box 영역 전체 합 급여항목

    List<Map<String, String>> getPaymentDateList(Map<String, String> requestMap);   // 지급일 데이터 리스트_모달
    List<Map<String, String>> getsalAllowList(Map<String, String> requestMap);  // 전체 급여항목 리스트 조회
    List<Map<String, String>> getNonTaxSalAllowList(Map<String, String> requestMap);    // 비과세 항목 리스트
    int mergeSalAllowPay(List<SaAllowPay> newSaAllowPayList );  // 급여항목 입력 + 수정
    SaAllow getSalAllowInfo(SaAllowPay saAllowPay); // 급여자료
    int deleteSalAllowPay(SaAllowPay saAllowPay);   // 급여항목 지우기
//    int insertSalAllowPay(SaAllowPay saAllowPay);   //급여항목
//    int updateSalAllowPay(SaAllowPay saAllowPay);

    int updateDate(Map<String, String> requestMap); // 완료여부 고치기

    int copyLastMonthData(List<SaAllowPay> saAllowPay); // 전월데이터 복사

    int copyDeductPayByLastMonthData(List<SaDeductPay> saDeductPay); // 공제항목 전월데이터 복사

    Map<String, String> getSumAllowPayByYnTax(Map<String, String> requestMap); // (날짜별 사원별) 과세 비과세 합

    void makeDateId(SaAllowPay saAllowPay);
    String getDateId(SaAllowPay saAllowPay);
    int getSalAllowPaySumByMonth(Map<String, String> requestMap);

    Map<String,Integer> getSalAllowPaySumTaxY(SaAllowPay saAllowPay);
    int insertSalAllow(SaAllow saAllow);
    int updateSalAllow(SaAllow saAllow);
    int deleteSalAllow(SaAllow saAllow);
    int updateNonTaxLimit(SaAllowPay saAllowPay);

    String createSallowSeq();
    SaAllow getSalAllow(SaAllow saAllow);
    int selectCountByAllow(SaAllow saAllow);
    List<SaAllowPay> getDateListByLastMonth(SaAllowPay saAllowPay);
    int deleteSalAllowPayThisMonth(SaAllowPay saAllowPay);
    int deleteSalDeductPayThisMonth(SaAllowPay saAllowPay);
    List<SaAllowPay> getSaAllowPayListByLastMonthPaymentDate(SaAllowPay saAllowPay);
    List<SaDeductPay> getSaDeductPayListByLastMonthPaymentDate(SaAllowPay saAllowPay);
}
