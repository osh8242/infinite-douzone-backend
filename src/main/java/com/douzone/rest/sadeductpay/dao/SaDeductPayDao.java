package com.douzone.rest.sadeductpay.dao;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaDeductPayDao {

    List<SaDeductPay> getSaDeductPayByCdEmp(SaDeductPay saDeductPay);   // 공제항목 리스트 조회
    public void updateSaDeductPay(SaDeductPay saDeductPay);//수정

}
