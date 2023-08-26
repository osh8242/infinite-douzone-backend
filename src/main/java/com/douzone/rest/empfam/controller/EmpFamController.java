package com.douzone.rest.empfam.controller;

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

    @GetMapping("/getAll")
    public ResponseEntity<List> getAllEmpFam() {
        System.out.println("empfam/getAll 컨트롤러 진입!");
        List<EmpFam> empfamList = empFamService.getAllEmpFam();
        return new ResponseEntity<>(empfamList, HttpStatus.OK);
    }

    @PostMapping("/getEmpFamListByCdEmp")
    public ResponseEntity<List<EmpFam>> getEmpFamListByCdEmp(@RequestBody EmpFam empfam){
        System.out.println("EmpFamController.getEmpFamListByCdEmp");
        List<EmpFam> result = null;
        result = empFamService.getEmpFamListByCdEmp(empfam);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public int insertEmpFam(@RequestBody EmpFam empfam){
        System.out.println("empfam/insert 컨트롤러 진입!");
        int result = empFamService.insertEmpFam(empfam);
        return result;
    }

    @PutMapping("/updateEmpFamBySeqValAndCdEmp")
    public int updateEmpFamBySeqValAndCdEmp(@RequestBody EmpFam empfam){
        System.out.println("empfam/update 컨트롤러 진입!");
        int result = empFamService.updateEmpFamBySeqValAndCdEmp(empfam);
        return result;
    }

    @PostMapping("/delete")
    public int deleteEmp(@RequestBody EmpFam empfam){
        System.out.println("empfam/delete 컨트롤러 진입!");
        int result = empFamService.deleteEmpFam(empfam);
        return result;
    }
}