package com.douzone.rest.empfam.service;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empfam.dao.EmpFamDao;
import com.douzone.rest.empfam.vo.EmpFam;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpFamService {

    private EmpFamDao empFamDao;

    @Autowired
    public EmpFamService(EmpFamDao empFamDao) {
        this.empFamDao = empFamDao;
    }

    public List<EmpFam> getAllEmpFam() {
        System.out.println("getAllEmpFam Service 진입!");
        return empFamDao.getAllEmpFam();
    }

    public List<EmpFam> getListByCdEmp(Emp emp) {
        return empFamDao.getListByCdEmp(emp.getCdEmp());
    }

    public EmpFam getEmpFamByCdEmpAndCdCalrel(EmpFam empfam) {
        System.out.println("getEmpFam Service 진입!");
        return empFamDao.getEmpFamByCdEmpAndCdCalrel(empfam);
    }

    public int insertEmpFam(EmpFam empfam) {
        System.out.println("insertEmpFam Service 진입!");
        int result = empFamDao.insertEmpFam(empfam);
        if(result != 0) System.out.println("insert 성공!");
        return result;
    }

    public int updateEmpFamByCdEmpAndCdCalrel(EmpFam empfam) {
        System.out.println("updateEmpFam Service 진입!");
        int result = empFamDao.updateEmpFamByCdEmpAndCdCalrel(empfam);
        if(result != 0) System.out.println("update 성공!");
        return result;
    }

    public int deleteEmpFam(EmpFam empfam) {
        System.out.println("deleteEmpFam Service 진입!");
        int result = empFamDao.deleteEmpFam(empfam);
        if(result != 0) System.out.println("delete 성공!");
        return result;
    }
}