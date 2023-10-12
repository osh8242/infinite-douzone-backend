package com.douzone.rest.emp.dao;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.emp.vo.EmpMenuUsage;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpDao {
    public List<Emp> getAllEmp();
    public List<Emp> getEmpListForHrManagement(Map<String, Object> map);

    public List<Emp> getEmpListForSwsm(Map<String, Object> map);

    public Emp getEmpByCdEmp(Emp emp);
    public List<Emp> getEmpListByVariable(Map<String, String> variable);
    public int insertEmp(Emp emp);
    public int updateEmp(Emp emp);
    public int deleteEmp(Emp emp);
//    public EmpMenuUsage getUndeletedEmpByCdEmp(Emp emp);


    //코드헬퍼
    public List<Emp> getEmpListForCodeHelper(Map<String, String> map);

    //더미데이터 생성
//    public int empDummy(Emp emp);
//    public int saAllowDummy(SaAllowPay saAllowPay);
}
