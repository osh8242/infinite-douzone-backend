package com.douzone.rest.common.controller;

import com.douzone.rest.common.service.CommonService;
import com.douzone.rest.common.vo.Code;
import com.douzone.rest.emp.dao.EmpDao;
import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
@CrossOrigin(origins = "http://localhost:3000/")
public class CommonController {

    private EmpDao empDao;

    private CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService, EmpDao empDao) {
        this.commonService = commonService;
        this.empDao = empDao;
    }

    @GetMapping("/getCodeListForCodeHelper")
    public ResponseEntity<List<Code>> getAllCode(@RequestParam(name = "parentId", required = true) String parentId){

        Code code = Code.builder()
                .parentId(parentId)
                .build();

        List<Code> result = commonService.getAllCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/makeDummyData")
//    public void makeDummyData(){
//
////        for (int i = 7; i < 500; i++) {
////            Emp emp = new Emp();
////            emp.setCdEmp(Integer.toString(i));
////            emp.setNmKrname("사원"+i);
////            empDao.empDummy(emp);
////        }
//
//        for (int i = 1; i < 500; i++) {
//            SaAllowPay saAllowPay = new SaAllowPay();
//            saAllowPay.setCdEmp(Integer.toString(i));
//            saAllowPay.setAllowPay("2000000");
//            saAllowPay.setDateId("3");
//            saAllowPay.setSalDivision("SAL");
//            saAllowPay.setYnTax("Y");
//            saAllowPay.setCdAllow("101");
//            empDao.saAllowDummy(saAllowPay);
//        }
//
//    }




}
