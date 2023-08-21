package com.douzone.rest.sadeductpay.dao;

import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaDeductPayDao {

    public void updateSaDeductPay(SaDeductPay saDeductPay);//수정

}
