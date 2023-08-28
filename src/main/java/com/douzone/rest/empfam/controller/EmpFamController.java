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
@RequestMapping("/empFam")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpFamController {
    @Autowired
    private EmpFamService empFamService;

    @GetMapping("/getAllEmpFam")
    public ResponseEntity<List> getAllEmpFam() {
        System.out.println("empFam/getAllEmpFam 컨트롤러 진입!");
        List<EmpFam> empfamList = empFamService.getAllEmpFam();
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("/getEmpFamListByCdEmp")
    public ResponseEntity<List> getEmpFamListByCdEmp(@RequestBody Emp emp) {
        System.out.println("getEmpFamListByCdEmp 컨트롤러 --------------------------------");
        List<EmpFam> empfamList = empFamService.getEmpFamListByCdEmp(emp);
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("/getEmpFamByCdEmpAndCdCalrel")
    public ResponseEntity<?> getEmpFamByCdEmpAndCdCalrel(@RequestBody EmpFam empfam){
        System.out.println("empFam/getEmpFamByCdEmpAndCdCalrel 컨트롤러 진입!");
        EmpFam result = empFamService.getEmpFamByCdEmpAndCdCalrel(empfam);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/insertEmpFam")
    public int insertEmpFam(@RequestBody EmpFam empfam){
        System.out.println("empFam/insert 컨트롤러 진입!");
        int result = empFamService.insertEmpFam(empfam);
        return result;
    }

    @PostMapping("/updateEmpFam")
    public int updateEmpFamByCdEmpAndCdCalrel(@RequestBody EmpFam empfam){
        System.out.println("empFam/update 컨트롤러 진입!");
        int result = empFamService.updateEmpFamByCdEmpAndCdCalrel(empfam);
        return result;
    }

    @PostMapping("/deleteEmpFam")
    public int deleteEmp(@RequestBody EmpFam empfam){
        System.out.println("empFam/delete 컨트롤러 진입!");
        int result = empFamService.deleteEmpFam(empfam);
        return result;
    }
}