package com.douzone.rest.swsm.service;

import com.douzone.rest.swsm.dao.SwsmOtherDao;
import com.douzone.rest.swsm.vo.SwsmOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwsmOtherService {
    @Autowired
    private SwsmOtherDao swsmOtherDao;

//    private SwsmOtherDao swsmOtherDao;
//
//    @Autowired
//    public SwsmOtherService(SwsmOtherDao swsmOtherDao) {
//        this.swsmOtherDao = swsmOtherDao;
//    }

    public List<SwsmOther> getAllSwsmOther() {
        System.out.println(swsmOtherDao.getAllSwsmOther());
        return swsmOtherDao.getAllSwsmOther();
    }

    public List<SwsmOther> getSwsmOtherByCdEmp(SwsmOther swsmOther) {
        List<SwsmOther> swsmOtherList = swsmOtherDao.getSwsmOtherByCdEmp(swsmOther);
        return swsmOtherList;
    }


//    public List<SwsmOther> getSwsmOtherListByCdEmp(SwsmOther swsmOther) {
//        return swsmOtherDao.getSwsmOtherListByCdEmp(swsmOther);
//    }

    public int insertSwsmOther(SwsmOther swsmOther) {
        return swsmOtherDao.insertSwsmOther(swsmOther);
    }

}
