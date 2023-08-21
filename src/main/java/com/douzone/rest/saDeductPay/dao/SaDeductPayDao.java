package com.douzone.rest.saDeductPay.dao;

import com.douzone.rest.saDeductPay.vo.SaDeductPay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaDeductPayDao {

    public void updateSaDeductPay(SaDeductPay saDeductPay);//수정

}
