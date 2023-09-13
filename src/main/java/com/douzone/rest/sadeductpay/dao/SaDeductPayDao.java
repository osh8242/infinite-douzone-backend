package com.douzone.rest.sadeductpay.dao;

import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaDeductPayDao {

    List<SaDeductPay> getSaDeductPayByCdEmp(Map<String, String> requestMap); // 공제항목 리스트 조회
    public void updateSaDeductPay(Map<String, Object> saDeductPay); //수정
    List<Map<String, String>> getSalDeductPaySum(Map<String, String> requestMap);

}
