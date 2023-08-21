package com.douzone.rest.emp.service;

import com.douzone.rest.emp.dao.EmpDao;
import com.douzone.rest.emp.vo.Emp;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Emp> getAllEmp(Emp emp){
        System.out.println("EmpService 진입");
        EmpDao empdao = sqlSessionTemplate.getMapper(EmpDao.class);
        List<Emp> result = empdao.getAllEmp(emp);
        return result;
    }

    public Emp getOneEmpByCdEmp(Emp emp){
        System.out.println("Emp Service -----");
        EmpDao empdao = sqlSessionTemplate.getMapper(EmpDao.class);
        Emp resultEmp = empdao.getOneEmpByCdEmp(emp);
        System.out.println("resultEmp= "+ resultEmp);
        return resultEmp;
    }

    public int insertEmp(Emp emp){
        System.out.println("Emp insert -----");
        EmpDao empdao = sqlSessionTemplate.getMapper(EmpDao.class);
        int result = empdao.insertEmp(emp);
        System.out.println("result= " + result);
        return result;
    }

    public int updateEmp(Emp emp){
        System.out.println("Emp update -----");
        EmpDao empdao = sqlSessionTemplate.getMapper(EmpDao.class);
        int result = empdao.updateEmp(emp);
        System.out.println("result= "+result);
        return result;
    }

    public int deleteEmp(Emp emp){
        System.out.println("Emp delete -----");
        EmpDao empDao = sqlSessionTemplate.getMapper(EmpDao.class);
        int result = empDao.deleteEmp(emp);
        System.out.println("result= "+result);
        return result;
    }
}
