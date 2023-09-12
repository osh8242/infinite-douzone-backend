package com.douzone.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping
    public String sendMessage(@RequestBody Sms sms){
        return null;
    }
}
