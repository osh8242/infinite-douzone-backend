package com.douzone.rest.saempinfo.dao;

import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaEmpInfoMapper {
    List<SaEmpInfo> getSaEmpInfoList(Map<String, String> reqestMap);//리스트조회

    SaEmpInfo getSaEmpInfoByCdEmp(Map<String, String> requestMap);//하나조회

    int insertSaEmpInfo(SaEmpInfo saEmpInfo);//삽입

    int deleteSaAllowPayEmpList(List<Map<String,String>> deleteEmpList);//삭제
    int deleteSaDeductEmpList(List<Map<String,String>> deleteEmpList);//삭제

    void updateSaEmpInfo(SaEmpInfo saEmpInfo);//수정

    Map<String, String> getDateInfo(Map<String, String> reqestMap);//날짜 id 검색
    int deleteDateId(String dateId);
}
