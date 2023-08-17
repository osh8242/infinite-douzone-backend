package com.douzone.rest.empadd.dao;

import com.douzone.rest.empadd.vo.EmpAdd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpAddDao {
    public List<EmpAdd> getAllEmpAdd();

    public EmpAdd getEmpAddByCD_EMP(EmpAdd empAdd);

    public int deleteEmpByCD_EMP(EmpAdd empAdd);

    public int updateEmpAddByCD_EMP(EmpAdd empAdd);

    public int insertEmpAdd(EmpAdd empAdd);
}
