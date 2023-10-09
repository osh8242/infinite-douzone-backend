package com.douzone.rest.swsm.controller;

import com.douzone.rest.emp.service.EmpService;
import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    private EmpService empservice;

    @GetMapping("/getAllSwsm")
    public ResponseEntity<List<Swsm>> getAllSwsm() {
        System.out.println("getAll");
        List<Swsm> swsmList = swsmService.getAllSwsm();
        return ResponseEntity.status(HttpStatus.OK).body(swsmList);
    }

    @DeleteMapping("/deleteSwsm")
    public ResponseEntity<Integer> deleteSwsm(@RequestBody Swsm swsm) {
        int result;
        result = swsmService.deleteSwsm(swsm);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getSwsmByCdEmp")
    public ResponseEntity<Swsm> getAllSwsmByCdEmp(@RequestBody Swsm swsm) {
        System.out.println("Controllerrr parameter;");
        System.out.println(swsm);
        Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);
        if (reswsm == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Swsm not found for given CdEmp"
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(reswsm);
    }

    @PostMapping("/insertSwsm")
    public ResponseEntity<Integer> insertSwsm(@RequestBody Swsm swsm) {
        System.out.println("swsmparmeter?" + swsm);
        System.out.println("EmpAddController.insertEmpAdd");
        System.out.println("swsm = " + swsm);
        int result;
        result = swsmService.insertSwsm(swsm);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getEmpAllListForSwsmDate")
    public ResponseEntity<List<Emp>> getEmpAllListForSwsmDate(@RequestParam(name = "date", defaultValue = "default") String date, @RequestParam(name = "dateEnd", defaultValue = "default") String dateEnd) {

        List<Emp> listByJobList;

        System.out.println("empall dateList임");
        listByJobList = empservice.getAllEmp();
        System.out.println("ListByJobListttt");
        System.out.println(listByJobList);

        // 기간 조회
        List<Emp> resultList = new ArrayList<>();
        for (Emp e : listByJobList) {
            String temp = e.getDateOfcreate().substring(0, 7);
            if (date.compareTo(temp) <= 0) {
                if (dateEnd.compareTo(temp) >= 0)
                    resultList.add(e);
            }
        }
//        List<Swsm> swsmResultList = new ArrayList<>();
//        for (Emp e : resultList){
//            Swsm swsm = new Swsm();
//            swsm.setCdEmp(e.getCdEmp());
//            Swsm s=swsmService.getSwsmByCdEmp(swsm);
//            swsmResultList.add(s);
//        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/getEmpListForSwsmDate")
    public ResponseEntity<List<Emp>> getEmpListForSwsmDate(@RequestParam(name = "job") String job, @RequestParam(name = "date", defaultValue = "default") String date, @RequestParam(name = "dateEnd", defaultValue = "default") String dateEnd) {
        if (job == null || date == null || dateEnd == null) {
            System.out.println("bad_+Requetst");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("empall Testing...");
        System.out.println("paramter job : " + job);
        System.out.println("date: " + date);
        System.out.println("dateEnd: " + dateEnd);

        List<Emp> listByJobList;
        if (job.equals("empAll")) {
            System.out.println("empall selected.");
            listByJobList = empservice.getAllEmp();
            System.out.println("ListByJob Result > > > > > " + listByJobList);
        } else {
            System.out.println("EmpController.getEmpListByJobClassfication");
            System.out.println("incomeClassfication || job = " + job);
            Map<String, Object> map = new HashMap<>();
            map.put("incomeClassfication", job.trim());
            listByJobList = empservice.getEmpListForSwsm(map);
        }

        System.out.println("IF FINSH AND AGAIN > > > > " + listByJobList);
        System.out.println("..... START SELECT BY DATE");
        // 기간 조회
        List<Emp> resultList = new ArrayList<>();
        for (Emp e : listByJobList) {
            System.out.println("PARAM : " + e.getCdEmp());
            if (e.getDateOfcreate() != null) { // Null Check 추가
                String temp = e.getDateOfcreate().substring(0, 7);
                if (date.compareTo(temp) <= 0) {
                    if (dateEnd.compareTo(temp) >= 0)
                        resultList.add(e);
                }
            } else {
                System.out.println("Date of creation is null for employee: " + e.getCdEmp());
            }
        }

        System.out.println("END SELECT BY DATE ....");

        System.out.println("RESULT: " + resultList);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

//    @GetMapping("/getEmpListForSwsmDateExceptJob")
//    public ResponseEntity<List<Emp>> getEmpListForSwsmDateExceptJob(@RequestParam(name = "date", defaultValue = "default") String date, @RequestParam(name = "dateEnd", defaultValue = "default") String dateEnd) {
//        List<Emp> listByJobList = new ArrayList<>();
//
//
//        listByJobList = empservice.getAllEmp();
//        System.out.println("TESTING...");
//        System.out.println(listByJobList);
//        // 기간 조회
////        List<Emp> resultList = new ArrayList<>();
////        for (Emp e : listByJobList) {
////            String temp = e.getDateOfcreate().substring(0, 7);
////            if (date.compareTo(temp) <= 0) {
////                if (dateEnd.compareTo(temp) >= 0)
////                    resultList.add(e);
////            }
////        }
//
//        return new ResponseEntity<>(listByJobList, HttpStatus.OK);
//    }


    // codehelper List load
    @GetMapping("/getSwsmListForSwsm")
    public ResponseEntity<List<Emp>> getSwsmListForSwsm(@RequestParam(name = "job") String job) {
        System.out.println("startttt");

        List<Emp> empList;

        if (job.equals("empAll")) {
            System.out.println("empall임");
            System.out.println(empservice.getAllEmp());
            empList = empservice.getAllEmp();
        } else {
            System.out.println("EmpController.getEmpListByJobClassfication");
            System.out.println("incomeClassfication || job = " + job);
            Map<String, Object> map = new HashMap<>();
            map.put("incomeClassfication", job.trim());
            empList = empservice.getEmpListForSwsm(map);

            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
            System.out.println(empList);
        }

        List<Emp> list = new ArrayList<>();
        for (Emp e : empList) {
            Swsm swsm = new Swsm();
            swsm.setCdEmp(e.getCdEmp());

            Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);

            if (reswsm != null) {
                list.add(e);
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 조회리스트들의 cd_emp 기준으로 내 테이블에 있는 값 읽어오기
    @GetMapping("/getCodeHelperList")
    public ResponseEntity<List<Emp>> getCodeHelperList(@RequestParam(name = "job") String job) {
        System.out.println("startttt");

        List<Emp> empList;

        if (job.equals("empAll")) {
            System.out.println("empall임");
            System.out.println(empservice.getAllEmp());
            empList = empservice.getAllEmp();
        } else {
            System.out.println("EmpController.getEmpListByJobClassfication");
            System.out.println("incomeClassfication || job = " + job);
            Map<String, Object> map = new HashMap<>();
            map.put("incomeClassfication", job.trim());
            empList = empservice.getEmpListForSwsm(map);

            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
            System.out.println(empList);
        }

        List<Emp> list = new ArrayList<>();
        for (Emp e : empList) {
            Swsm swsm = new Swsm();
            swsm.setCdEmp(e.getCdEmp());

            Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);

            if (reswsm == null) {
                list.add(e);
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getEmpListExceptSwsmList")
    public ResponseEntity<List<Emp>> getExceptList(@RequestParam(name = "job") String job) {
        System.out.println("startttt");

        List<Emp> empList;

        if (job.equals("empAll")) {
            System.out.println("empall임");
            System.out.println(empservice.getAllEmp());
            empList = empservice.getAllEmp();
        } else {
            System.out.println("EmpController.getEmpListByJobClassfication");
            System.out.println("incomeClassfication || job = " + job);
            Map<String, Object> map = new HashMap<>();
            map.put("incomeClassfication", job.trim());
            empList = empservice.getEmpListForSwsm(map);

            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
            System.out.println(empList);
        }

        List<Emp> list = new ArrayList<>();
        for (Emp e : empList) {
            Swsm swsm = new Swsm();
            swsm.setCdEmp(e.getCdEmp());

            Swsm reswsm = swsmService.getSwsmByCdEmp(swsm);

            if (reswsm != null) {
                list.add(e);
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PutMapping("/updateSwsm")
    public int updateSwsm(@RequestBody Swsm swsm) {
        System.out.println("controller update : parameter" + swsm);
        int result = swsmService.updateSwsm(swsm);

        System.out.println("result contoorlller row: " + result);
        System.out.println(result);

        return result;
    }
}