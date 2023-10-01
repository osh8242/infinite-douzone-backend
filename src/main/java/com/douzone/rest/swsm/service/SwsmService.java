package com.douzone.rest.swsm.service;

import com.douzone.rest.swsm.dao.SwsmDao;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SwsmService {
    @Autowired
    private SwsmDao swsmDao;

    public int updateSwsm(Swsm swsm) {
        System.out.println("Swsm swsm service update -----");
        System.out.println(swsm);
        int result = swsmDao.updateSwsm(swsm);
        System.out.println("result= service" + result);
        return result;
    }

    public Swsm getSwsmByCdEmp(Swsm swsm) {
//        Swsm r=null;
//        try {
        System.out.println("service: swsmByCdEmp parameter" + swsm.getCdEmp());
        Swsm r = swsmDao.getSwsmByCdEmp(swsm);
        System.out.println("service: swsmByCdEmp result" + r);
        System.out.println("getSwsmByCdEmp: " + r);

//        }catch (Exception e){
//            System.out.println("e message: "+e.getMessage());
//        }

        return r;
    }

    public int insertSwsm(Swsm swsm){
        int result = 0;
        result = swsmDao.insertSwsm(swsm);
        return result;
    }

    public List<Swsm> getSwsmListForSwsm(Map<String, String> map){
        List<Swsm> swsms = null;
        swsms = swsmDao.getSwsmListForSwsm(map);
        return swsms;
    }

    public int insertSwsmEmp(Swsm swsm) {
        return swsmDao.insertSwsmEmp(swsm);
    }

    public int updateSwsmByEmp(Swsm swsm) {
        return swsmDao.updateSwsmByEmp(swsm);
    }

    public List<Swsm> getCodeNameRrnByWithholdingYear(Swsm swsm) {
        return swsmDao.getCodeNameRrnByWithholdingYear(swsm);
    }

    public List<Swsm> getAllSwsm() {
        return swsmDao.getAllSwsm();
    }

//    public int updateSwsm(Swsm swsm) {
//        return swsmDao.updateSwsm(swsm);
//    }

//    public int insertSwsm(Swsm swsm) {
//        return swsmDao.insertSwsm(swsm);
//    }
//
//    public int deleteSwsm(Swsm swsm) {
//        return swsmDao.deleteSwsm(swsm);
//    }
//    public int insertSwsmByEmp(Swsm swsm) {
//        return swsmDao.insertSwsmByEmp(swsm);
//    }

}
