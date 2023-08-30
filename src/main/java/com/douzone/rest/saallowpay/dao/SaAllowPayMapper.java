package com.douzone.rest.saallowpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaAllowPayMapper {
    List<SaAllowPay> getSalAlLowPayList(SaAllowPay saAllowPay);   // 급여항목 리스트 조회
    int insertSalAllowPay(SaAllowPay saAllowPay);   //급여자료 입력
    int updateSalAllowPay(SaAllowPay saAllowPay);   // 급여항목 수정

    List<SaAllowPay> getSalAllowPaySum(SaAllowPay saAllowPay);

//    List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo);//리스트조회
//    void deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제
//    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

}
