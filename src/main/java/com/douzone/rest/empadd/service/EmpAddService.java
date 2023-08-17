package com.douzone.rest.empadd.service;

import com.douzone.rest.empadd.dao.EmpAddDao;
import com.douzone.rest.empadd.vo.EmpAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpAddService {

    private EmpAddDao empAddDao;
    @Autowired
    public EmpAddService(EmpAddDao empAddDao) {
        this.empAddDao = empAddDao;
    }

    public List<EmpAdd> getAllEmpAdd(){
        List<EmpAdd> empAdds = null;
        empAdds = empAddDao.getAllEmpAdd();
        return empAdds;
    }

    public int insertEmpAdd(EmpAdd empAdd){
        int result = 0;
        result = empAddDao.insertEmpAdd(empAdd);
        return result;
    }

    public int deleteEmpByCD_EMP(EmpAdd empAdd){
        int result = 0;
        result = empAddDao.deleteEmpByCD_EMP(empAdd);
        return result;
    }

    public int updateEmpAddByCD_EMP(EmpAdd empAdd){
        int result = 0;
        result = empAddDao.updateEmpAddByCD_EMP(empAdd);
        return result;
    }

}
