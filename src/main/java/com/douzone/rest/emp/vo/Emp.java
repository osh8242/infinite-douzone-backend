package com.douzone.rest.emp.vo;

import lombok.Data;

@Data
public class Emp {
    // custom type을 위해 추가된 field
    private String combinedAddress; //zipHome, addHome1 - 우편번호와 상세주소1
    private String telHome; //telHome1, telHome2, telHome3 - 전화번호
    private String celEmp ; // celEmp1, celEmp2, celEmp3 - 모바일번호

    // 기본 Emp field
    private String cdEmp;
    private String nmKrname;
    private String ynFor;
    private String noSocial;
    private String jobOk;
    private String daEnter;
    private String fgSex;
    private String abbNation;
    private String ynResident;
    private String str3911;
    private String str4011;
    private String cdNation;
    private String zipHome;
    private String addHome1;
    private String addHome2;
    private String telHome1;
    private String telHome2;
    private String telHome3;
    private String celEmp1;
    private String celEmp2;
    private String celEmp3;
    private String emEmp;
    private String idMsn;
    private String cdDept;
    private String cdOccup;
    private String rankNo;
    private String cdSalcls;
    private String cdField;
    private String cdProject;
    private String daRetire;
    private String cdBank;
    private String noBnkacct;
    private String nmBnkowner;
    private String mn611;
    private String cdAcctit1;
    private String cdAcctit2;
    private String fgPaysupply;
    private String mnMmaverage;
    private String mnNatptarget;
    private String mnNatpslev;
    private String mnHostarget;
    private String mnHos;
    private String noHealth;
    private String mnEmptarget;
    private String mnEmp;
    private String ynEmploy;
    private String ynCeo;
    private String ynSanjae;
    private String ynLonginsur;
    private String str3211;
    private String str3311;
    private String str3511;
    private String mn2111;
    private String mn3111;
    private String str1111;
    private String str4511;
    private String str4611;
    private String str4711;
    private String mn1111;
    private String ynDanil;
    private String ynIclman;
    private String str4311;
    private String str4411;
    private String mnIclpay;
    private String str3411;
    private String ynForlabor;
    private String ynUnit;
    private String ynOverwork;
    private String str4211;
    private String mn4111;
    private String incomeClassfication;
    private String dateOfcreate;

}
