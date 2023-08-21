package com.douzone.rest.emp.dao;

import com.douzone.rest.emp.vo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpDao {
    public List<Emp> getAllEmp();
    public Emp getOneEmp();
    public int insertEmp(Emp emp);
    public int updateEmp(Emp emp);
    public int deleteEmp(Emp emp);
}
