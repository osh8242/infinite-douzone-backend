package com.douzone.rest.sallowpay.dao;

import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import com.douzone.rest.sallowpay.vo.SaLowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaLowPayMapper {
    List<SaLowPay> getSalLowPayList(SaLowPay saLowPay);   // 급여항목 리스트 조회
    void updateSalowPay(SaLowPay saLowPay);   // 급여항목 수정

//    List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo);//리스트조회
//    void deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제
//    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

}
