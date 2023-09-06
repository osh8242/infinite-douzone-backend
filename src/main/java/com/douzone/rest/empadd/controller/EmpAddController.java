package com.douzone.rest.empadd.controller;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empadd.service.EmpAddService;
import com.douzone.rest.empadd.vo.EmpAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empAdd")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpAddController {

    private EmpAddService empAddService;

    @Autowired
    public EmpAddController(EmpAddService empAddService) {
        this.empAddService = empAddService;
    }

    @GetMapping("/getAllEmpAdd")
    public ResponseEntity<List<EmpAdd>> getAllEmpAdd() {
        List<EmpAdd> empAdds = null;
        empAdds = empAddService.getAllEmpAdd();
        return ResponseEntity.ok(empAdds);
    }

    @GetMapping("/getEmpAddListForHrManagement")
    public ResponseEntity<List<EmpAdd>> getEmpAddListForHrManagement(@RequestParam(name = "jobOk") String jobOk,
                                                                  @RequestParam(name = "orderRef", required = false) String orderRef,
                                                                  @RequestParam(name = "refYear", required = false) String refYear) {
        System.out.println("EmpAddController.getEmpAddListForHrManagement");
        Map<String, String> map = new HashMap<>();
        map.put("jobOk", jobOk.trim());
        if(refYear != null ) map.put("refYear", refYear.trim());
        map.put("orderRef", orderRef.trim());
        List<EmpAdd> list = null;
        list = empAddService.getEmpAddListForHrManagement(map);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/getEmpAddByCdEmp")
    public ResponseEntity<EmpAdd> getAllEmpAdd(@RequestBody EmpAdd empAdd) {
        empAdd = empAddService.getEmpAddByCdEmp(empAdd);
        return ResponseEntity.ok(empAdd);
    }

    @PostMapping("/insertEmpAdd")
    public ResponseEntity<Integer> insertEmpAdd(@RequestBody EmpAdd empAdd) {
        int result = 0;
        result = empAddService.insertEmpAdd(empAdd);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteEmpAdd")
    public ResponseEntity<Integer> deleteEmpByCD_EMP(@RequestBody EmpAdd empAdd){
        int result = 0;
        result = empAddService.deleteEmpByCdEmp(empAdd);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/updateEmpAdd")
    public ResponseEntity<Integer> updateEmpAddByCD_EMP(@RequestBody EmpAdd empAdd){
        System.out.println("EmpAddController.deleteEmpByCD_EMP");
        System.out.println("empAdd = " + empAdd);
        int result = 0;
        result = empAddService.updateEmpAddByCdEmp(empAdd);
        return ResponseEntity.ok(result);
    }

}
