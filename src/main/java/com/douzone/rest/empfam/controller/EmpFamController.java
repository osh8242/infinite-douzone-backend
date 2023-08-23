package com.douzone.rest.empfam.controller;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empfam.service.EmpFamService;
import com.douzone.rest.empfam.vo.EmpFam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empfam/*")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpFamController {
    @Autowired
    private EmpFamService empFamService;

    @GetMapping("getAll")
    public ResponseEntity<List> getAllEmpFam() {
        System.out.println("empfam/getAll 컨트롤러 진입!");
        List<EmpFam> empfamList = empFamService.getAllEmpFam();
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("getAllByCdEmp")
    public ResponseEntity<List> getAllEmpFamByCdEmp(@RequestBody Emp emp) {
        System.out.println("getAllEmpFamByCdEmp 컨트롤러 --------------------------------");
        List<EmpFam> empfamList = empFamService.getAllEmpFamByCdEmp(emp);
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("getOne")
    public ResponseEntity<?> getOneEmpFamByCdEmpAndCdCalrel(@RequestBody EmpFam empfam){
        System.out.println("empfam/getOne 컨트롤러 진입!");
        EmpFam result = empFamService.getOneEmpFambyCdEmp(empfam);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("insert")
    public int insertEmpFam(@RequestBody EmpFam empfam){
        System.out.println("empfam/insert 컨트롤러 진입!");
        int result = empFamService.insertEmpFam(empfam);
        return result;
    }

    @PostMapping("update")
    public int updateEmpFamByCdEmpAndCdCalrel(@RequestBody EmpFam empfam){
        System.out.println("empfam/update 컨트롤러 진입!");
        int result = empFamService.updateEmpFamByCdEmpAndCdCalrel(empfam);
        return result;
    }

    @PostMapping("delete")
    public int deleteEmp(@RequestBody EmpFam empfam){
        System.out.println("empfam/delete 컨트롤러 진입!");
        int result = empFamService.deleteEmpFam(empfam);
        return result;
    }
}