package com.douzone.rest.swsm.controller;

import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empadd.service.EmpAddService;
import com.douzone.rest.empadd.vo.EmpAdd;
import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/swsm")
@CrossOrigin(origins = "http://localhost:3000/")
public class SwsmController {

    private SwsmService swsmService;

    @Autowired
    public SwsmController(SwsmService swsmService) {
        System.out.println("Swsm Controller============================");
        this.swsmService = swsmService;
    }

//    @Autowired
//    private SwsmService swsmService;

    @Autowired
    private EmpService empService;

    @GetMapping("/getAllSwsm")
    public ResponseEntity<List<Swsm>> getAllSwsm() {
        System.out.println("getAll");
        List<Swsm> swsmList = swsmService.getAllSwsm();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }

    @PostMapping("/getSwsmByCdEmp")
    public ResponseEntity<Swsm> getAllSwsmByCdEmp(@RequestBody Swsm swsm) {
        System.out.println("================================================");
//        Swsm reswsm = null;
//        try {
            System.out.println("controller : parameter " + swsm.getCdEmp());
            Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);
            System.out.println("controller : swsmByCdemp " + reswsm);
//        } catch (Exception e) {
//            System.out.println("e messgae: "+e.getMessage());
//        }
        return ResponseEntity.status(HttpStatus.OK).body(reswsm);
    }


//    @PostMapping("/getSwsmByCdEmp")
//    public ResponseEntity<Swsm> getSwsmByCdEmp(@RequestBody Swsm swsm) {
//        System.out.println("d" + swsm);
//        Swsm resultSwsm = null;
////       System.out.println(resultSwsm);
//
//        List<Swsm> swsmList = swsmService.getAllSwsm();
//        for (Swsm s : swsmList) {
//            if (s.getCdEmp().equals(swsm.getCdEmp()))
//                resultSwsm = s;
//        }
//        return ResponseEntity.ok(resultSwsm);
//    }
//
//    @GetMapping("/getSwsmByCdEmp")
//    public ResponseEntity<List<Swsm>> getAllByCdEmp(@RequestBody Swsm swsm) {
//
//        System.out.println("getSwsmByCdEmp Controller ------------");
//        List<Swsm> swsmList = swsmService.getAllSwsm();
//        List<Emp> empList = empService.getAllEmp();
//        List<Swsm> resultList = new ArrayList<>();
//
//        for (Swsm s : swsmList) {
//            System.out.println(swsmList);
//            for (Emp e : empList) {
//                System.out.println("emp------------" + empList);
//                if (s.getCdEmp().equals(e.getCdEmp())) {
//                    resultList.add(s);
//                    System.out.println(s.getCdEmp() + ", " + e.getCdEmp());
//                    System.out.println("result: " + resultList);
//                }
//            }
//        }
//
//        System.out.println(resultList);
//        System.out.println("===================================");
//
////        return ResponseEntity.ok(empAdd);
//        return ResponseEntity.status(HttpStatus.OK).body(resultList);
//    }

    // 원천년도 기준 select code, name, rrn
//    @GetMapping("/getCodeNameRrnByWithholdingYear")
//    public ResponseEntity<List<Swsm>> getCodeNameRrnByWithholdingYear(@RequestBody Swsm swsm) {
//        List<Swsm> swsmList = swsmService.getCodeNameRrnByWithholdingYear(swsm);
//        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
//    }
//
//    @PostMapping("/insertSwsmEmp")
//    public ResponseEntity<Integer> insertSwsmEmp(@RequestBody Swsm swsm) {
//        int result = swsmService.insertSwsmEmp(swsm);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @PostMapping("/updateSwsmByEmp")
//    public ResponseEntity<Integer> updateSwsmByEmp(@RequestBody Swsm swsm) {
//        return ResponseEntity.status(HttpStatus.OK).body(swsmService.updateSwsmByEmp(swsm));
//    }

//    @PostMapping("/updateSwsm")
//    public ResponseEntity<Integer> updateSwsm(@RequestBody Swsm swsm) {
//        return ResponseEntity.status(HttpStatus.OK).body(swsmService.updateSwsm(swsm));
//    }


//    @PostMapping("/insertSwsm")
//    public ResponseEntity<Integer> insertSwsm(@RequestBody Swsm swsm) {
//        int result = swsmService.insertSwsm(swsm);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @DeleteMapping("/deleteSwsm")
//    public ResponseEntity<Integer> deleteSwsm(@RequestBody Swsm swsm) {
//        return ResponseEntity.status(HttpStatus.OK).body(swsmService.deleteSwsm(swsm));
//    }
//
//
//    // 선택 사원 기준 insert
//    @PostMapping("/insertSwsmByEmp")
//    public ResponseEntity<Integer> insertSwsmByEmp(@RequestBody Swsm swsm) {
//        int result = swsmService.insertSwsmByEmp(swsm);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

}
