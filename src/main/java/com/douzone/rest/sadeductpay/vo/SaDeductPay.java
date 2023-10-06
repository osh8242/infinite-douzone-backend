package com.douzone.rest.sadeductpay.vo;

import lombok.Data;

@Data
public class SaDeductPay {
    private String cdEmp;           //사원코드
    private String allowMonth;      //귀속월
    private String allowYear;       //귀속년
    private String cdDeduct;        //공제항목코드
    private String allowPay;        //공제항목 지급금액
    private String paymentDate;     //지급일

    private String nmDeduct;        //공제항목 이름
    private String sumDeductPay;    //공제항목 합계
    private String dateId;          // dateId

    private String salDivision;     // 지급구분 조회
    private String ynBonus;
    private String ynSal;
    private String rate;
}
