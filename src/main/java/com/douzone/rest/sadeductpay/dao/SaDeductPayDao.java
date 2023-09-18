package com.douzone.rest.sadeductpay.dao;

import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaDeductPayDao {

    List<SaDeductPay> getSaDeductPayByCdEmp(Map<String, String> requestMap); // 공제항목 리스트 조회
    public int updateSaDeductPayList(List<SaDeductPay> saDeductPayList); //수정
    List<Map<String, String>> getSalDeductPaySum(Map<String, String> requestMap);

    public List<Map<String, String>> getsalDeductList(Map<String, String> requestMap);
}
