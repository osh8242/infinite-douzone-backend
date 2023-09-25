package com.douzone.rest.saempinfo.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.saempinfo.dao.SaEmpInfoMapper;
import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
            Map<String, String> dateInfo = saEmpInfoMapper.getDateInfo(requestMap);

            if(dateInfo!=null) {
                requestMap.put("dateId", dateInfo.get("dateId"));
                result.put("dateId", dateInfo);
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

    public int deleteSaEmpList(List<Map<String,String>> deleteEmpList) {
        int result = 0;
        try {

            saEmpInfoMapper.deleteDateId(deleteEmpList.get(0).get("dateId"));   // dateId 삭제
            saEmpInfoMapper.deleteSaAllowPayEmpList(deleteEmpList); // 급여지급 삭제
            saEmpInfoMapper.deleteSaDeductEmpList(deleteEmpList);   // 공제 지급 삭제
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }

    public void updateSaEmpInfo(SaEmpInfo saEmpInfo) {
        try {
            saEmpInfoMapper.updateSaEmpInfo(saEmpInfo);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
