package com.douzone.rest.emp.service;

import com.douzone.rest.emp.dao.EmpDao;
import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empfam.dao.EmpFamDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpService {

    private EmpDao empDao;

    @Autowired
    public EmpService(EmpDao empDao) {
        this.empDao = empDao;
    }

    public List<Emp> getAllEmp(){
        System.out.println("EmpService 진입");
        List<Emp> result = empDao.getAllEmp();
        System.out.println(result);
        return result;
    }

    public Emp getEmpByCdEmp(Emp emp){
        System.out.println("Emp Service -----");
        Emp resultEmp = empDao.getEmpByCdEmp(emp);
        System.out.println("resultEmp= "+ resultEmp);
        return resultEmp;
    }

    public List<Emp> getEmpListByVariable(Map<String, String> variable){
//        String columnName1 =variable.get("columnName");
//        String columnValue2 =variable.get("columnValue");
        return empDao.getEmpListByVariable(variable);
    }

    public int insertEmp(Emp emp){
        System.out.println("Emp insert -----");
        int result = empDao.insertEmp(emp);
        System.out.println("result= " + result);
        return result;
    }

    public int updateEmp(Emp emp){
        System.out.println("Emp update -----");
        int result = empDao.updateEmp(emp);
        System.out.println("result= "+result);
        return result;
    }

    public int deleteEmp(Emp emp){
        System.out.println("Emp delete -----");
        int result = empDao.deleteEmp(emp);
        System.out.println("result= "+result);
        return result;
    }
}
