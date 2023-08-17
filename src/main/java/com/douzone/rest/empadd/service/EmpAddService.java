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

    public Integer insertEmpAdd(EmpAdd empAdd){
        Integer result = null;
        result = empAddDao.insertEmpAdd(empAdd);
        return result;
    }
}