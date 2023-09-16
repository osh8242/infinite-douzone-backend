package com.douzone.rest.common.service;

import com.douzone.rest.common.dao.CommonDao;
import com.douzone.rest.common.vo.Code;
import com.douzone.rest.emp.dao.EmpDao;
import com.douzone.rest.emp.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    private CommonDao commonDao;

    @Autowired
    public CommonService(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public List<Code> getAllCode(Code code){
        System.out.println(code.getParentId());
        List<Code> result = commonDao.getAllCode(code);
        return result;
    }

}
