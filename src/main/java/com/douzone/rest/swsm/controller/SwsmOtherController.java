package com.douzone.rest.swsm.controller;

import com.douzone.rest.empadd.vo.EmpAdd;
import com.douzone.rest.swsm.service.SwsmOtherService;
import com.douzone.rest.swsm.vo.Swsm;
import com.douzone.rest.swsm.vo.SwsmOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swsmOther")
@CrossOrigin(origins = "http://localhost:3000/")
public class SwsmOtherController {

    @Autowired
    private SwsmOtherService swsmOtherService;

    @GetMapping("/getAllSwsmOther")
    public ResponseEntity<List<SwsmOther>> getAllSwsmOther() {
        System.out.println("swsmOther get All");
        List<SwsmOther> swsmOtherList = swsmOtherService.getAllSwsmOther();
        System.out.println(swsmOtherList);
        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
    }

    @PostMapping("/getSwsmOtherByCdEmp")
    public ResponseEntity<List<SwsmOther>> getSwsmOtherByCdEmp(@RequestBody SwsmOther swsmOther) {
        List<SwsmOther> swsmOtherList = swsmOtherService.getSwsmOtherByCdEmp(swsmOther);
        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
    }


    @PostMapping("/insertSwsmOther")
    public ResponseEntity<Integer> insertSwsmOther(@RequestBody SwsmOther swsmOther) {
        int result = 0;
        result = swsmOtherService.insertSwsmOther(swsmOther);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/updateSwsmOtherByCdEmp")
    public ResponseEntity<Integer> updateSwsmOther(@RequestBody SwsmOther swsmOther) {
        System.out.println("update pararmeter");
        System.out.println(swsmOther);
        int result = 0;
        result = swsmOtherService.updateSwsmOther(swsmOther);
        System.out.println("update swsomtoehr result: "+result);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteSwsmOther")
    public int deleteSwsmOther(@RequestBody SwsmOther swsmOther){
        return swsmOtherService.deleteSwsmOther(swsmOther);
    }

//    @PostMapping("/getSwsmOtherByCdEmp")
//    public ResponseEntity<List<SwsmOther>> getSwsmOtherByCdEmp(SwsmOther swsmOther){
//        System.out.println("==============================================");
//        System.out.println("SOController parameter: ");
//        System.out.println(swsmOther);
//        List<SwsmOther> SOList =swsmOtherService.getSwsmOtherByCdEmp(swsmOther);
//        System.out.println("contoller result: "+SOList);
//        return ResponseEntity.status(HttpStatus.OK).body(SOList);
//    }
//    @PostMapping("/getSwsmOtherListByCdEmp")
//    public ResponseEntity<List<SwsmOther>> getSwsmOtherListByCdEmp(@RequestBody SwsmOther swsmOther) {
//        List<SwsmOther> swsmOtherList = swsmOtherService.getSwsmOtherListByCdEmp(swsmOther);
//        System.out.println(swsmOtherList);
//        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
//    }


//    @PostMapping("/insertSwsmOther")
//    public ResponseEntity<Integer> insertSwsmOther(@RequestBody SwsmOther swsmOther) {
//        int result = swsmOtherService.insertSwsmOther(swsmOther);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//

}
