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

//    private SwsmDao swsmDao;
//
//    @Autowired
//    public SwsmService(SwsmDao swsmDao) {
//        this.swsmDao = swsmDao;
//    }

    @Autowired
    private SwsmDao swsmDao;

    public int insertSwsm(Swsm swsm) {
        return swsmDao.insertSwsm(swsm);
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
