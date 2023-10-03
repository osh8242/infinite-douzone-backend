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
    public ResponseEntity<Integer> deleteSwsm(@RequestBody Swsm swsm){
        int result = 0;
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
        System.out.println("EmpAddController.insertEmpAdd");
        System.out.println("swsm = " + swsm);
        int result = 0;
        result = swsmService.insertSwsm(swsm);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getEmpAllListForSwsmDate")
    public ResponseEntity<List<Emp>> getEmpAllListForSwsmDate(@RequestParam(name = "date", defaultValue = "default") String date, @RequestParam(name = "dateEnd", defaultValue = "default") String dateEnd) {

        List<Emp> listByJobList = new ArrayList<>();

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

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/getEmpListForSwsmDate")
    public ResponseEntity<List<Emp>> getEmpListForSwsmDate(@RequestParam(name = "job") String job, @RequestParam(name = "date", defaultValue = "default") String date, @RequestParam(name = "dateEnd", defaultValue = "default") String dateEnd) {
        if (job == null || date == null || dateEnd == null) {
            System.out.println("bad_+Requetst");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("dateee Test GO!");
        System.out.println(date);
        System.out.println(dateEnd);
        System.out.println(job);

        List<Emp> listByJobList = new ArrayList<>();
        if (job.equals("empAll")) {
            System.out.println("empall dateList임");
            listByJobList = empservice.getAllEmp();
            System.out.println("ListByJobListttt");
            System.out.println(listByJobList);
        } else {
            System.out.println("EmpController.getEmpListByJobClassfication");
            System.out.println("incomeClassfication || job = " + job);
            Map<String, Object> map = new HashMap<>();
            map.put("incomeClassfication", job.trim());
            listByJobList = empservice.getEmpListForSwsm(map);
        }

        // 기간 조회
        List<Emp> resultList = new ArrayList<>();
        for (Emp e : listByJobList) {
            String temp = e.getDateOfcreate().substring(0, 7);
            if (date.compareTo(temp) <= 0) {
                if (dateEnd.compareTo(temp) >= 0)
                    resultList.add(e);
            }
        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


    // 조회 조회기준으로 가지고 온 리스트
//    @GetMapping("/getEmpListForSwsm")
//    public ResponseEntity<List<Emp>> getEmpListForSwsm(@RequestParam(name = "job") String job,
//                                                       @RequestParam(name = "date") String date, @RequestParam(name = "dateEnd") String dateEnd) {
//        System.out.println("dateEnd" + dateEnd);
//        System.out.println("date:" + date);
//
//        List<Emp> jobList = new ArrayList<>();
//        if (job.equals("empAll")) {
//            System.out.println("empall임");
//            System.out.println(empservice.getAllEmp());
//            jobList = empservice.getAllEmp();
//
//            // 임시 테스트로 all 에서만 혹인중
//            for (Emp e : jobList) {
//                System.out.println(e.getDaEnter());
//            }
//            return new ResponseEntity<>(jobList, HttpStatus.OK);
//        } else {
//            System.out.println("EmpController.getEmpListByJobClassfication");
//            System.out.println("incomeClassfication || job = " + job);
//            Map<String, Object> map = new HashMap<>();
//            map.put("incomeClassfication", job.trim());
//            jobList = empservice.getEmpListForSwsm(map);
//
//            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
//            System.out.println(jobList);
//
//            return new ResponseEntity<>(jobList, HttpStatus.OK);
//        }
//        result = checkPeriod(jobList, date, dateEnd);
//        // success 면 그 해당하는 emp만 반환
//        if (result.equals("SUCCESS")) {
//            return new ResponseEntity<>(jobList, HttpStatus.OK);
//        } else {
// }
//        }


    /// check period


    // null 이면 http not oke returnhhh


//    public List<Emp> checkPeriod(List<Emp> list, String date, String dateEnd) {
//        return null;
//    }


//    // 조회기준으로 가지고 온 리스트
//    @GetMapping("/getEmpListForSwsm")
//    public ResponseEntity<List<Emp>> getEmpListForSwsm(@RequestParam(name = "job") String job) {
//        if (job.equals("empAll")) {
//            System.out.println("empall임");
//            System.out.println(empservice.getAllEmp());
//            return new ResponseEntity<>(empservice.getAllEmp(), HttpStatus.OK);
//        } else {
//            System.out.println("EmpController.getEmpListByJobClassfication");
//            System.out.println("incomeClassfication || job = " + job);
//            Map<String, Object> map = new HashMap<>();
//            map.put("incomeClassfication", job.trim());
//            List<Emp> list = null;
//            list = empservice.getEmpListForSwsm(map);
//
//            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
//            System.out.println(list);
//
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        }
//    }

    // 조회기준으로 가지고 온 리스트
//    @GetMapping("/getEmpListForSwsm")
//    public List<Emp> getEmpListForSwsm(@RequestParam(name = "job") String job) {
//        List<Emp> list = null;
//        if (job.equals("empAll")) {
//            System.out.println("empall임");
//            System.out.println(empservice.getAllEmp());
//            list = empservice.getAllEmp();
////            return new ResponseEntity<>(empservice.getAllEmp(), HttpStatus.OK);
//        } else {
//            System.out.println("EmpController.getEmpListByJobClassfication");
//            System.out.println("incomeClassfication || job = " + job);
//            Map<String, Object> map = new HashMap<>();
//            map.put("incomeClassfication", job.trim());
//            list = empservice.getEmpListForSwsm(map);
//
//            System.out.println("conrtrlllerrr getEmpilist for wmwmw");
//            System.out.println(list);
//
////            return new ResponseEntity<>(list, HttpStatus.OK);
//        }
//        return list;
//    }

    // codehelper List load
    @GetMapping("/getSwsmListForSwsm")
    public ResponseEntity<List<Emp>> getSwsmListForSwsm(@RequestParam(name = "job") String job) {
        System.out.println("startttt");

        List<Emp> empList = new ArrayList<>();

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

        List<Emp> empList = new ArrayList<>();

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

        List<Emp> empList = new ArrayList<>();

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
