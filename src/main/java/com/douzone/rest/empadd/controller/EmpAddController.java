package com.douzone.rest.empadd.controller;

import com.douzone.rest.empadd.service.EmpAddService;
import com.douzone.rest.empadd.vo.EmpAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empAdd")
public class EmpAddController {

    private EmpAddService empAddService;

    @Autowired
    public EmpAddController(EmpAddService empAddService) {
        this.empAddService = empAddService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmpAdd>> getAllEmpAdd() {
        List<EmpAdd> empAdds = null;
        empAdds = empAddService.getAllEmpAdd();
        return ResponseEntity.ok(empAdds);
    }
    @GetMapping("/getEmpAddByCdEmp")
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
//    public EmpAdd getEmpAddByCD_EMP(EmpAdd empAdd);
//    public int deleteEmpByCD_EMP(EmpAdd empAdd);
//    public int updateEmpAddByCD_EMP(EmpAdd empAdd);
//    public int insertEmpAdd(EmpAdd empAdd);

}
