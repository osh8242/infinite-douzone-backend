package com.douzone.rest.saallowpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaAllowPayMapper {
    List<SaAllowPay> getSalAlLowPayListByEmp(Map<String, String> requestMap);   // 급여항목 리스트 조회
    int insertDate(SaAllowPay saAllowPay);   // date 테이블 insert
    int insertSalAllowPay(SaAllowPay saAllowPay);   //급여자료 입력

    int updateSalAllowPay(Map<String, String> requestMap);   // 급여항목 수정
    List<Map<String, String>> getSalAllowPaySum(Map<String, String> requestMap);
    List<Map<String, String>> getPaymentDateList(Map<String, String> requestMap);

//    List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo);//리스트조회
//    void deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제
//    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

}
