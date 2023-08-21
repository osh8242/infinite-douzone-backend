package com.douzone.rest.swsm.service;

import com.douzone.rest.empadd.dao.EmpAddDao;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.rest.swsm.dao.SwsmDao;
import com.douzone.rest.swsm.dao.SwsmDao;

import java.util.List;

@Service
public class SwsmService {
    //    @Autowired
//    private SwsmDao swsmDao;
    private SwsmDao swsmDao;

    @Autowired
    public SwsmService(SwsmDao swsmDao) {
        this.swsmDao = swsmDao;
    }

    public int insertSwsm(Swsm swsm) {
        return swsmDao.insertSwsm(swsm);
    }

    public int insertSwsmByEmp(Swsm swsm) {
        return swsmDao.insertSwsmByEmp(swsm);
    }

    public int updateSwsmByEmp(Swsm swsm) {
        return swsmDao.updateSwsmByEmp(swsm);
    }


    public List<Swsm> getCodeNameRrnByWithholdingYear(Swsm swsm) {
        return swsmDao.getCodeNameRrnByWithholdingYear(swsm);
    }

    public int updateSwsm(Swsm swsm) {
        return swsmDao.updateSwsm(swsm);
    }

    public int deleteSwsm(Swsm swsm) {
        return swsmDao.deleteSwsm(swsm);
    }

    public List<Swsm> getAllSwsm() {
        return swsmDao.getAllSwsm();
    }

}
