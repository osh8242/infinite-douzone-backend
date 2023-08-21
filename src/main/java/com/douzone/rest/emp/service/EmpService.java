package com.douzone.rest.emp.service;

import com.douzone.rest.emp.dao.EmpDao;
import com.douzone.rest.emp.vo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private SqlSession sqlSession;

    public List<Emp> getAllEmp(){
        System.out.println("EmpService 진입");
        EmpDao empdao = sqlSession.getMapper(EmpDao.class);
        List<Emp> result = empdao.getAllEmp();
        return result;
    }

    public  Emp getOneEmp(){
        System.out.println("Emp Service -----");
        EmpDao empdao = sqlSession.getMapper(EmpDao.class);
        Emp emp = empdao.getOneEmp();
        System.out.println("emp= "+ emp);
        return emp;
    }

    public int insertEmp(Emp emp){
        System.out.println("Emp insert -----");
        EmpDao empdao = sqlSession.getMapper(EmpDao.class);
        int result = empdao.insertEmp(emp);
        System.out.println("result= " + result);
        return result;
    }

    public int updateEmp(Emp emp){
        System.out.println("Emp update -----");
        EmpDao empdao = sqlSession.getMapper(EmpDao.class);
        int result = empdao.updateEmp(emp);
        System.out.println("result= "+result);
        return result;
    }

    public int deleteEmp(Emp emp){
        System.out.println("Emp delete -----");
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        int result = empDao.deleteEmp(emp);
        System.out.println("result= "+result);
        return result;
    }
}
