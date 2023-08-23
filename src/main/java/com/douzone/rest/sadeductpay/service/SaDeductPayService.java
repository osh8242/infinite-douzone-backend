package com.douzone.rest.sadeductpay.service;

import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaDeductPayService {

    private SaDeductPayDao saDeductPayDao;
    @Autowired
    public SaDeductPayService(SaDeductPayDao saDeductPayDao) {
        this.saDeductPayDao = saDeductPayDao;
    }

    public List<SaDeductPay> getSaDeductPayByCdEmp(SaDeductPay saDeductPay) {
        return saDeductPayDao.getSaDeductPayByCdEmp(saDeductPay);
    }

    public void updateSaDeductPay(SaDeductPay saDeductPay){
        saDeductPayDao.updateSaDeductPay(saDeductPay);
    }
}
