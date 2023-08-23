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
public class SwsmOtherController {

    @Autowired
    private SwsmOtherService swsmOtherService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SwsmOther>> getAllSwsmOther() {
        List<SwsmOther> swsmOtherList = swsmOtherService.getAllSwsmOther();
        return ResponseEntity.status(HttpStatus.OK).body(swsmOtherList);
    }


    @PostMapping("/insertSwsmOther")
    public ResponseEntity<Integer> insertSwsmOther(@RequestBody SwsmOther swsmOther) {
        int result = swsmOtherService.insertSwsmOther(swsmOther);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}
