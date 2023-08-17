package com.douzone.rest.saEmpInfo.service;

import com.douzone.rest.saEmpInfo.dao.SaEmpInfoMapper;
import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaEmpInfoService {

    SaEmpInfoMapper saEmpInfoMapper;
    @Autowired
    public SaEmpInfoService(SaEmpInfoMapper saEmpInfoMapper){
        this.saEmpInfoMapper = saEmpInfoMapper;
    }

    public List<SaEmpInfo> getAllSaEmpInfo(){
        return saEmpInfoMapper.getAllSaEmpInfo();
    }

    public void deleteEmpInfo(SaEmpInfo saEmpInfo){
        try {
            saEmpInfoMapper.deleteEmpInfo(saEmpInfo);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

}
