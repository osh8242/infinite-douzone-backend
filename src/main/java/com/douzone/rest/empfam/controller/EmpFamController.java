package com.douzone.rest.empfam.controller;

import com.douzone.rest.empfam.service.EmpFamService;
import com.douzone.rest.empfam.vo.EmpFam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empfam/*")
public class EmpFamController {
    @Autowired
    private EmpFamService empFamService;

    @GetMapping("getAll")
    public ResponseEntity<List> getAllEmpFam() {
        System.out.println("empfam/getAll 컨트롤러 진입!");
        List<EmpFam> empfamList = empFamService.getAllEmpFam();
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("getOne")
    public ResponseEntity<?> getOneEmpFamByCdEmpAndCdCalrel(@RequestBody EmpFam empfam){
        System.out.println("empfam/getOne 컨트롤러 진입!");
        EmpFam result = empFamService.getOneEmpFamByCdEmpAndCdCalrel(empfam);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("insert")
    public int insertEmpFam(@RequestBody EmpFam empfam){
        System.out.println("empfam/insert 컨트롤러 진입!");
        int result = empFamService.insertEmpFam(empfam);
        return result;
    }

    @PostMapping("update")
    public int updateEmpFam(@RequestBody EmpFam empfam){
        System.out.println("empfam/update 컨트롤러 진입!");
        int result = empFamService.updateEmpFam(empfam);
        return result;
    }

    @PostMapping("delete")
    public int deleteEmp(@RequestBody EmpFam empfam){
        System.out.println("empfam/delete 컨트롤러 진입!");
        int result = empFamService.deleteEmpFam(empfam);
        return result;
    }
}