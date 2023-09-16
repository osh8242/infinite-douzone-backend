package com.douzone.rest.saallowpay.dao;

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

    int insertSalAllowPay(SaAllowPay saAllowPay);   //급여자료 입력
    int updateSalAllowPay(Map<String, String> requestMap);   // 급여항목 수정


}
