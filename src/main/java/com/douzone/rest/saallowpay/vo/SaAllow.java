package com.douzone.rest.saallowpay.vo;

import lombok.Data;

@Data
public class SaAllow {
    private String cdAllow;             // 급여항목 코드
    private String nmAllow;             // 급여항목 이름
    private String ynTax;               // 과세/비과세 여부
    private String salDivision;          // 구분(상여, 급여)
    private String monthlyYn;           // 월정액급여 포함 여부
    private String commonlyYn;          // 통상임금 포함여부
    private String nonTaxLimit;         // 비과세항목 한도
    private String nonTaxDivision;      // 비과세 항목 구분
    private String originYnTax;
    private String ynUse;               // 사용여부
}
