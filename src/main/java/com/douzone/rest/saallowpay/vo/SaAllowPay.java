package com.douzone.rest.saallowpay.vo;

import lombok.Data;

@Data
public class SaAllowPay {
    private String cdAllow;     // 급여항목 코드
    private String allowPay;    // 급여항목 지급금액
    private String cdEmp;       // 사원코드
    private String dateId;      // 날짜 key


    private String allowMonth;  // 귀속월
    private String allowYear;   // 귀속년
    private String paymentDate; // 지급일


    private String ynTax;       // 과세여부
    private String nmAllow;     //급여항목 이름
    private String sumAllowPay; //지급금액 합계

    private String salDivision;
    private String calculation;

    private String commonlyYn;
    private String monthlyYn;
    private String nontaxLimit;
    private String ynUse;
}
