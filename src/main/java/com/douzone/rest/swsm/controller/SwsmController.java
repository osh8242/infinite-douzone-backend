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

    //   @Autowired
    //    private SwsmService swsmService;

    private SwsmService swsmService;

    @Autowired
    public SwsmController(SwsmService swsmService){
        this.swsmService=swsmService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Swsm>> getAllSwsm(){
        List<Swsm> swsmList=swsmService.getAllSwsm();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }


    @PostMapping("/insertSwsm")
    public ResponseEntity<Integer> insertSwsm(@RequestBody Swsm swsm){
        int result=swsmService.insertSwsm(swsm);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}
