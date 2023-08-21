package com.douzone.rest.saDeductPay.service;

import com.douzone.rest.empadd.dao.EmpAddDao;
import com.douzone.rest.saDeductPay.dao.SaDeductPayDao;
import com.douzone.rest.saDeductPay.vo.SaDeductPay;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaDeductPayService {

    private SaDeductPayDao saDeductPayDao;
    @Autowired
    public SaDeductPayService(SaDeductPayDao saDeductPayDao) {
        this.saDeductPayDao = saDeductPayDao;
    }


    public void updateSaDeductPay(SaDeductPay saDeductPay){
        saDeductPayDao.updateSaDeductPay(saDeductPay);
    }
}
