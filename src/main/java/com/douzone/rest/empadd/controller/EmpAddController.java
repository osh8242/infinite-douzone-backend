package com.douzone.rest.empadd.controller;

import com.douzone.rest.empadd.service.EmpAddService;
import com.douzone.rest.empadd.vo.EmpAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empadd")
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

    @PostMapping("/insertEmpAdd")
    public ResponseEntity<Integer> insertEmpAdd(@RequestBody EmpAdd empAdd) {
        int result = 0;
        System.out.println("empAdd = " + empAdd);
        result = empAddService.insertEmpAdd(empAdd);
        return ResponseEntity.ok(result);
    }
//    public EmpAdd getEmpAddByCD_EMP(EmpAdd empAdd);
//    public int deleteEmpByCD_EMP(EmpAdd empAdd);
//    public int updateEmpAddByCD_EMP(EmpAdd empAdd);
//    public int insertEmpAdd(EmpAdd empAdd);

}
