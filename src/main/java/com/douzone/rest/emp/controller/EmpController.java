package com.douzone.rest.emp.controller;

import com.douzone.rest.company.config.RoutingCompanyDataSource;
import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.emp.vo.EmpMenuUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    private EmpService empservice;
    @Autowired
    public EmpController(EmpService empservice) {
        this.empservice = empservice;
    }

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Emp>> getAllEmp(){
        System.out.println("Emp Controller 진입");
        List<Emp> result = empservice.getAllEmp();
        System.out.println("result = " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getEmpListForHrManagement")
    public ResponseEntity<List<Emp>> getEmpListForHrManagement(@RequestParam(name = "jobOk") String jobOk,
                                                               @RequestParam(name = "orderRef") String orderRef,
                                                               @RequestParam(name = "refYear", required = false) String refYear) {
        System.out.println("EmpController.getEmpListByJobOk");
        Map<String, Object> map = new HashMap<>();
        map.put("jobOk", jobOk.trim());
        if(refYear != null ) map.put("refYear", refYear.trim());
        map.put("orderRef", orderRef.trim());
        List<Emp> list = null;
        list = empservice.getEmpListForHrManagement(map);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/getEmpByCdEmp")
    public ResponseEntity<Emp> getEmpByCdEmp(@RequestBody Emp emp){
        System.out.println("Emp getEmpByCdEmp Controller -----");
        Emp resultEmp = empservice.getEmpByCdEmp(emp);
        return new ResponseEntity<>(resultEmp, HttpStatus.OK);
    }

//    {columnName: 컬럼명, columnValue: 컬럼값} 으로 전달받아 검색하는 기능
    @PostMapping("/getEmpListByVariable")
    public ResponseEntity<List<Emp>> getEmpListByVariable(@RequestBody Map<String, String> variable){
        System.out.println("---------- Emp getEmpListByVariable Controller 시작 ----------");
        List<Emp> result = empservice.getEmpListByVariable(variable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("insertEmp")
    public int insertEmp(@RequestBody Emp emp) {
        System.out.println("Emp insert Controller -----");
        int result = empservice.insertEmp(emp);
        return result;
    }

    @PutMapping("/updateEmp")
    public int updateEmp(@RequestBody Emp emp){
        System.out.println("Emp update Controller -----");
        int result = empservice.updateEmp(emp);
        return result;
    }

    @DeleteMapping("/deleteEmp")
    public ResponseEntity<EmpMenuUsage> deleteEmp(@RequestBody Emp emp){
        System.out.println("Emp delete Controller -----");
        EmpMenuUsage empMenuUsage =empservice.deleteEmp(emp);
        System.out.println(empMenuUsage);
        return new ResponseEntity<>(empMenuUsage, HttpStatus.OK);
    }

    @GetMapping("/getEmpListForCodeHelper")
    public ResponseEntity<List<Emp>> getEmpListForCodeHelper(@RequestParam Map<String, String> reqestMap) {
        System.out.println(reqestMap.toString());
        List<Emp> result = empservice.getEmpListForCodeHelper(reqestMap);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
