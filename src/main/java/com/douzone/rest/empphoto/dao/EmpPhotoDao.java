package com.douzone.rest.empphoto.dao;

import com.douzone.rest.empphoto.vo.EmpPhoto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpPhotoDao {
    public EmpPhoto getEmpPhotoByCdEmp(String cdEmp);
    public int insertEmpPhoto(EmpPhoto empPhoto);
    public int updateEmpPhoto(EmpPhoto empPhoto);
}
