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
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<EmpFam> getAllEmpFam() {
        System.out.println("getAllEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        return empfamdao.getAllEmpFam();
    }

    public List<EmpFam> getAllEmpFamByCdEmp(Emp emp) {
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        String cdEmp = emp.getCdEmp();
        return empfamdao.getAllEmpFamByCdEmp(cdEmp);
    }

    public EmpFam getOneEmpFambyCdEmp(EmpFam empfam) {
        System.out.println("getOneEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        return empfamdao.getOneEmpFambyCdEmp(empfam);
    }

    public int insertEmpFam(EmpFam empfam) {
        System.out.println("insertEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        int result = empfamdao.insertEmpFam(empfam);
        if(result != 0) System.out.println("insert 성공!");
        return result;
    }

    public int updateEmpFamByCdEmpAndCdCalrel(EmpFam empfam) {
        System.out.println("updateEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        int result = empfamdao.updateEmpFamByCdEmpAndCdCalrel(empfam);
        if(result != 0) System.out.println("update 성공!");
        return result;
    }

    public int deleteEmpFam(EmpFam empfam) {
        System.out.println("deleteEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSessionTemplate.getMapper(EmpFamDao.class);
        int result = empfamdao.deleteEmpFam(empfam);
        if(result != 0) System.out.println("delete 성공!");
        return result;
    }
}