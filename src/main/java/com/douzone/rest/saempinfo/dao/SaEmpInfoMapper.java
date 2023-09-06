package com.douzone.rest.saempinfo.dao;

import com.douzone.rest.saempinfo.vo.SaEmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaEmpInfoMapper {
    List<SaEmpInfo> getSaEmpInfoList(Map<String, Object> reqestMap);//리스트조회

    SaEmpInfo getSaEmpInfoByCdEmp(Map<String, String> requestMap);//하나조회

    int insertSaEmpInfo(SaEmpInfo saEmpInfo);//삽입

    int deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제

    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

    String getDateId(Map<String, Object> reqestMap);//날짜 id 검색
}
