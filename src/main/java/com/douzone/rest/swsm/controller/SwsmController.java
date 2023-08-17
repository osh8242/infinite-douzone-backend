package com.douzone.rest.swsm.controller;

import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SwsmController {

    @Autowired
    private SwsmService swsmService;

    @GetMapping("getAllList")
    public ResponseEntity<List<Swsm>> swsmList(){
        List<Swsm> swsmList=swsmService.swsmList();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }


}
