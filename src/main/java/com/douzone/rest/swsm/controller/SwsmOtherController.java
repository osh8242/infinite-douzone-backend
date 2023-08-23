package com.douzone.rest.swsm.controller;

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
        List<SwsmOther> swsmOtherList = swsmOtherService.getAllSwsmOther();
        System.out.println(swsmOtherList);
        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
    }

    @GetMapping("/getSwsmOtherListByEmpCode")
    public ResponseEntity<List<SwsmOther>> getSwsmOtherListByEmpCode() {
        List<SwsmOther> swsmOtherList = swsmOtherService.getAllSwsmOther();        System.out.println(swsmOtherList);

        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
    }


    @PostMapping("/insertSwsmOther")
    public ResponseEntity<Integer> insertSwsmOther(@RequestBody SwsmOther swsmOther) {
        int result = swsmOtherService.insertSwsmOther(swsmOther);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
