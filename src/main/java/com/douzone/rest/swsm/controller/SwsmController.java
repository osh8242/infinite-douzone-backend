package com.douzone.rest.swsm.controller;

import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/swsm")
public class SwsmController {

    @Autowired
    private SwsmService swsmService;

    @Autowired
    public SwsmController(SwsmService swsmService){
        this.swsmService=swsmService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Swsm>> swsmList(){
        List<Swsm> swsms=swsmService.swsmList();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }


}
