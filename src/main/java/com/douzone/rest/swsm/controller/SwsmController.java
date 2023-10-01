package com.douzone.rest.swsm.controller;

import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/swsm")
@CrossOrigin(origins = "http://localhost:3000/")
public class SwsmController {

    @Autowired
    private SwsmService swsmService;

    @Autowired
    public SwsmController(SwsmService swsmService) {
        this.swsmService = swsmService;
    }

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
        System.out.println("Controllerrr parameter;");
        System.out.println(swsm);
        Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);
        if(reswsm == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Swsm not found for given CdEmp"
            );
        }
            return ResponseEntity.status(HttpStatus.OK).body(reswsm);
    }

    @GetMapping("/getSwsmListForSwsm")
    public ResponseEntity<List<Swsm>> getSwsmListForSwsm(@RequestParam(name = "job") String job
                                                                   ) {
        Map<String, String> map = new HashMap<>();
        map.put("incomeClassfication", job.trim());
        List<Swsm> list = null;
        list = swsmService.getSwsmListForSwsm(map);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/insertSwsm")
    public ResponseEntity<Integer> insertSwsm(@RequestBody Swsm swsm) {
        System.out.println("EmpAddController.insertEmpAdd");
        System.out.println("swsm = " + swsm);
        int result = 0;
        result = swsmService.insertSwsm(swsm);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/updateSwsm")
    public int updateSwsm(@RequestBody Swsm swsm){
        System.out.println("controller update : parameter"+swsm);
        int result = swsmService.updateSwsm(swsm);

        System.out.println("result contoorlller row: "+result);
        System.out.println(result);

        return result;
    }
}
