package com.douzone.rest.empadd.dao;

import com.douzone.rest.empadd.vo.EmpAdd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpAddDao {
    public List<EmpAdd> getAllEmpAdd();

    public List<EmpAdd> getEmpAddListForHrManagement(Map<String, String> map);

    public EmpAdd getEmpAddByCdEmp(EmpAdd empAdd);

    public int deleteEmpByCdEmp(EmpAdd empAdd);

    public int updateEmpAddByCdEmp(EmpAdd empAdd);

    public int insertEmpAdd(EmpAdd empAdd);
}