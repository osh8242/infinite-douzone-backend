package com.douzone.rest.saallowpay.vo;

import lombok.Data;

@Data
public class SaAllowPay {
    private String cdAllow;     //급여항목
    private String allowPay;    //급여항목 지급금액
    private String cdEmp;       //사원코드
    private String allowMonth;  //귀속월
    private String allowYear;   //귀속년
    private String paymentDate; //지급일
}
