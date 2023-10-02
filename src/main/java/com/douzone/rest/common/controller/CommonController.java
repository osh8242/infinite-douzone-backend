package com.douzone.rest.common.controller;

import com.douzone.rest.common.service.CommonService;
import com.douzone.rest.common.vo.Code;
import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
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

    private CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/getCodeListForCodeHelper")
    public ResponseEntity<List<Code>> getAllCode(@RequestParam(name = "parentId", required = true) String parentId){

        Code code = Code.builder()
                .parentId(parentId)
                .build();

        List<Code> result = commonService.getAllCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
