package com.douzone.rest.empfam.dao;

import com.douzone.rest.empfam.vo.EmpFam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpFamDao {
    public List<EmpFam> getAllEmpFam();
    public List<EmpFam> getEmpFamListByCdEmp(String cdEmp);
    public EmpFam getEmpFamByCdEmpAndCdCalrel(EmpFam empfam);
    public int insertEmpFam(EmpFam empfam);
    public int updateEmpFamByCdEmpAndCdCalrel(EmpFam empFam);
    public int deleteEmpFam(EmpFam empfam);
}