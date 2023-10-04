package com.douzone.rest.emp.vo;

import lombok.Data;

@Data
public class Emp {
    // custom type을 위해 추가된 field
//    private String combinedAddress; //zipHome, addHome1 - 우편번호와 상세주소1
//    private String telHome; //telHome1, telHome2, telHome3 - 전화번호
//    private String celEmp ; // celEmp1, celEmp2, celEmp3 - 모바일번호

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
    private String ynDanil;
    private String ynIclman;
    private String mnIclpay;
    private String ynForlabor;
    private String ynUnit;
    private String ynOverwork;
    private String incomeClassfication;
    private String dateOfcreate;

}