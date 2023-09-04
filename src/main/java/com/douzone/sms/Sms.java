package com.douzone.sms;

import lombok.Data;

@Data
public class Sms {
    private String message;
    private String phoneNumber;

    Sms(String message, String phoneNumber){
        this.message=message;
        this.phoneNumber=phoneNumber   ;
    }

}
