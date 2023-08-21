package com.douzone.rest.empfam.dao;

import com.douzone.rest.empFam.vo.EmpFam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpFamDao {
    public List<EmpFam> getAllEmpFam();
    public EmpFam getOneEmpbyCdEmpFam(EmpFam empfam);
    public int insertEmpFam(EmpFam empfam);
    public int updateEmpFam(EmpFam empFam);
    public int deleteEmpFam(EmpFam empfam);
}