package com.douzone.rest.saallowpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaAllowPayMapper {
    List<SaAllowPay> getSaAlLowPayList(SaAllowPay saAllowPay);   // 급여항목 리스트 조회
    void updateSalowPay(SaAllowPay saAllowPay);   // 급여항목 수정

//    List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo);//리스트조회
//    void deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제
//    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

}
