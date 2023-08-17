package com.douzone.rest.saEmpInfo.dao;

import com.douzone.rest.saEmpInfo.vo.SaEmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaEmpInfoMapper {
    List<SaEmpInfo> getAllSaEmpInfo();//전체조회

    SaEmpInfo getSaEmpInfoByCdEmp(SaEmpInfo saEmpInfo);//하나조회
    
    void deleteEmpInfo(SaEmpInfo saEmpInfo);//삭제


    

}
