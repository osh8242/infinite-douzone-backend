package com.douzone.rest.saempinfo.service;

import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaEmpInfoService {

    SaEmpInfoMapper saEmpInfoMapper;

    @Autowired
    public SaEmpInfoService(SaEmpInfoMapper saEmpInfoMapper) {
        this.saEmpInfoMapper = saEmpInfoMapper;
    }

    public Map<String, Object> getAll(Map<String, Object> reqestMap) {

        Map<String, Object> result = new HashMap<>();
        try {
            String dateId = saEmpInfoMapper.getDateId(reqestMap);
            if(dateId!=null) {
                reqestMap.put("dateId", dateId);
                result.put("dateId", dateId);
                result.put("plist", saEmpInfoMapper.getSaEmpInfoList(reqestMap));
                //result.put("totalSalPaydata",saEmpInfoMapper.getSaEmpInfoList(reqestMap))
                //내일 합시다
            }
        }catch (Exception e){
            e.getStackTrace();
        }

        return result;
    }

    public SaEmpInfo getSaEmpInfoByCdEmp(Map<String, String> requestMap) {
        return saEmpInfoMapper.getSaEmpInfoByCdEmp(requestMap);
    }

    public int insertSaEmpInfo(SaEmpInfo saEmpInfo) {
        int result = 0;
        try{
            result = saEmpInfoMapper.insertSaEmpInfo(saEmpInfo);
        } catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public int deleteSaEmpInfo(SaEmpInfo saEmpInfo) {
        int result = 0;
        try {
            saEmpInfoMapper.deleteSaEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public void updateEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoMapper.updateEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
