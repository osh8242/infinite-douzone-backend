package com.douzone.rest.emp.controller;

import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpController {

    @Autowired
    private EmpService empservice;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmp(){
        System.out.println("Emp Controller 진입");
        List<Emp> result = empservice.getAllEmp();
        System.out.println("result = "+result);
        return new ResponseEntity<List>(result, HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOneEmpByCdEmp(@RequestBody Emp emp){
        System.out.println("Emp getOneEmpByCdEmp Controller -----");
        Emp resultEmp = empservice.getOneEmpByCdEmp(emp);
        return new ResponseEntity<>(resultEmp, HttpStatus.OK);
    }

    @PutMapping("/insert")
    public int insertEmp(@RequestBody Emp emp){
        System.out.println("Emp insert Controller -----");
        int result = empservice.insertEmp(emp);
        return result;
    }

    @PostMapping("/update")
    public int updateEmp(@RequestBody Emp emp){
        System.out.println("Emp update Controller -----");
        int result = empservice.updateEmp(emp);
        return result;
    }

    @PostMapping("/delete")
    public int deleteEmp(@RequestBody Emp emp){
        System.out.println("Emp delete Controller -----");
        int result =empservice.deleteEmp(emp);
        return result;
    }
}
