package com.douzone.rest.auth.mail;

import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.swsm.service.SwsmOtherService;
import com.douzone.rest.swsm.service.SwsmService;
import com.douzone.rest.swsm.vo.Swsm;
import com.douzone.rest.swsm.vo.SwsmOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = {"http://localhost:3000", "http://osh8242.iptime.org"})
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private SendMailComponent emailCompoenet;

    @Autowired
    private SwsmService swsmService;

    @Autowired
    private SwsmOtherService swsmOtherService;

        @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/sendMailSwsm")
    public ResponseEntity<ResponseVo> sendMailSwsm(@RequestBody UserVo user, @RequestParam String cdEmp) {
        System.out.println("ready for mail"+cdEmp);
        System.out.println(user);

        Swsm emailSwsm=new Swsm();
        emailSwsm.setCdEmp(cdEmp);
        Swsm mailSwsmVo = swsmService.getSwsmByCdEmp(emailSwsm);

        SwsmOther emailSwsmOther=new SwsmOther();
        emailSwsmOther.setCdEmp(cdEmp);
        List<SwsmOther> mailSwsmOtherList = swsmOtherService.getSwsmOtherByCdEmp(emailSwsmOther);


        emailCompoenet.sendTableForm(mailSwsmVo, mailSwsmOtherList, user);

        ResponseVo response = new ResponseVo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
