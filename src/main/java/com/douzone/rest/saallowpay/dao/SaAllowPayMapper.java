package com.douzone.rest.saallowpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllow;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaAllowPayMapper {
    List<SaAllowPay> getSalAlLowPayListByEmp(Map<String, String> requestMap);   // 급여항목 리스트 조회
    public List<Map<String, String>> getSalAllowPaySum(Map<String, String> requestMap);

    public List<Map<String, String>> getPaymentDateList(Map<String, String> requestMap);
    public List<Map<String, String>> getsalAllowList(Map<String, String> requestMap);
    public List<Map<String, String>> getNonTaxSalAllowList(Map<String, String> requestMap);

    public int mergeSalAllowPay(List<SaAllowPay> newSaAllowPayList );  // 급여자료 입력

    public SaAllow getSalAllowInfo(SaAllowPay saAllowPay);
    public int deleteSalAllowPay(SaAllowPay saAllowPay);

}
