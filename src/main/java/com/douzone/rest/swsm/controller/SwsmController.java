package com.douzone.rest.swsm.controller;

import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swsm")
public class SwsmController {

    @Autowired
    private SwsmService swsmService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Swsm>> getAllSwsm() {
        List<Swsm> swsmList = swsmService.getAllSwsm();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }

    // 원천년도 기준 select code, name, rrn
    @GetMapping("/getCodeNameRrnByWithholdingYear")
    public ResponseEntity<List<Swsm>> getCodeNameRrnByWithholdingYear(@RequestBody Swsm swsm) {
        List<Swsm> swsmList = swsmService.getCodeNameRrnByWithholdingYear(swsm);
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }

    @PostMapping("/insertSwsmEmp")
    public ResponseEntity<Integer> insertSwsmEmp(@RequestBody Swsm swsm) {
        int result = swsmService.insertSwsmEmp(swsm);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/updateSwsmByEmp")
    public ResponseEntity<Integer> updateSwsmByEmp(@RequestBody Swsm swsm) {
        return ResponseEntity.status(HttpStatus.OK).body(swsmService.updateSwsmByEmp(swsm));
    }
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
