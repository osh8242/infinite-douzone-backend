package com.douzone.rest.saempinfo.service;

import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaEmpInfoService {

    SaEmpInfoMapper saEmpInfoMapper;

    @Autowired
    public SaEmpInfoService(SaEmpInfoMapper saEmpInfoMapper) {
        this.saEmpInfoMapper = saEmpInfoMapper;
    }

    public List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo) {
        return saEmpInfoMapper.getSaEmpInfoList(saEmpInfo);
    }

    public SaEmpInfo getSaEmpInfoByCdEmp(SaEmpInfo saEmpInfo) {
        return saEmpInfoMapper.getSaEmpInfoByCdEmp(saEmpInfo);
    }

    public void deleteSaEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoMapper.deleteSaEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoMapper.updateEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
