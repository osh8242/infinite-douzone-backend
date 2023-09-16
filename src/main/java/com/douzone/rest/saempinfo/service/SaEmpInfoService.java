package com.douzone.rest.saempinfo.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SaEmpInfoService {

    SaEmpInfoMapper saEmpInfoMapper;

    SaAllowPayMapper saAllowPayMapper;

    SaDeductPayDao saDeductPayDao;

    @Autowired
    public SaEmpInfoService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductPayDao saDeductPayDao,
            SaEmpInfoMapper saEmpInfoMapper
    ) {
        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductPayDao = saDeductPayDao;
        this.saEmpInfoMapper = saEmpInfoMapper;
    }

    public Map<String, Object> getAll(Map<String, String> requestMap) {

        Map<String, Object> result = new HashMap<>();
        try {
            String dateId = saEmpInfoMapper.getDateId(requestMap);

            if(dateId!=null) {
                requestMap.put("dateId", dateId);
                result.put("dateId", dateId);
                result.put("plist", saEmpInfoMapper.getSaEmpInfoList(requestMap));

                Map<String, Object> totalSalPaydata = new HashMap<>();
                totalSalPaydata.put("salAllow", saAllowPayMapper.getSalAllowPaySum(requestMap)); //지급항목
                totalSalPaydata.put("salDeduct", saDeductPayDao.getSalDeductPaySum(requestMap)); //공제항목
                result.put("totalSalPaydata",totalSalPaydata);
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
