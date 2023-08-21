package com.douzone.rest.empfam.service;

import com.douzone.rest.empfam.dao.EmpFamDao;
import com.douzone.rest.empfam.vo.EmpFam;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpFamService {
    @Autowired
    private SqlSession sqlSession;

    public List<EmpFam> getAllEmpFam() {
        System.out.println("getAllEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSession.getMapper(EmpFamDao.class);
        return empfamdao.getAllEmpFam();
    }

    public EmpFam getOneEmpFamByCdEmpAndCdCalrel(EmpFam empfam) {
        System.out.println("getOneEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSession.getMapper(EmpFamDao.class);
        return empfamdao.getOneEmpFamByCdEmpAndCdCalrel(empfam);
    }

    public int insertEmpFam(EmpFam empfam) {
        System.out.println("insertEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSession.getMapper(EmpFamDao.class);
        int result = empfamdao.insertEmpFam(empfam);
        if(result != 0) System.out.println("insert 성공!");
        return result;
    }

    public int updateEmpFam(EmpFam empfam) {
        System.out.println("updateEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSession.getMapper(EmpFamDao.class);
        int result = empfamdao.updateEmpFam(empfam);
        if(result != 0) System.out.println("update 성공!");
        return result;
    }

    public int deleteEmpFam(EmpFam empfam) {
        System.out.println("deleteEmpFam Service 진입!");
        EmpFamDao empfamdao = sqlSession.getMapper(EmpFamDao.class);
        int result = empfamdao.deleteEmpFam(empfam);
        if(result != 0) System.out.println("delete 성공!");
        return result;
    }
}