package com.douzone.rest.saEmpInfo.dao;

import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaEmpInfoMapper {
    List<SaEmpInfo> getSaEmpInfoList(SaEmpInfo saEmpInfo);//리스트조회

    SaEmpInfo getSaEmpInfoByCdEmp(SaEmpInfo saEmpInfo);//하나조회
    
    void deleteSaEmpInfo(SaEmpInfo saEmpInfo);//삭제
    void updateEmpInfo(SaEmpInfo saEmpInfo);//수정

}
