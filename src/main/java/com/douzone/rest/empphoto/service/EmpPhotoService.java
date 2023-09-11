package com.douzone.rest.empphoto.service;

import com.douzone.rest.empphoto.dao.EmpPhotoDao;
import com.douzone.rest.empphoto.vo.EmpPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpPhotoService {
    private EmpPhotoDao empPhotoDao;
    @Autowired
    public EmpPhotoService(EmpPhotoDao empPhotoDao) {
        this.empPhotoDao = empPhotoDao;
    }




    public EmpPhoto getEmpPhotoByCdEmp (String cdEmp){
        EmpPhoto result = null;
        result = empPhotoDao.getEmpPhotoByCdEmp(cdEmp);
        return result;
    }

    public int insertEmpPhoto(EmpPhoto empPhoto){
        int result = 0;
        result = empPhotoDao.insertEmpPhoto(empPhoto);
        return result;

    }

    public int updateEmpPhoto(EmpPhoto empPhoto){
        int result = 0;
        result = empPhotoDao.updateEmpPhoto(empPhoto);
        return result;

    }
}
