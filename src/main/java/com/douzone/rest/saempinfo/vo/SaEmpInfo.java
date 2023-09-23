package com.douzone.rest.saempinfo.vo;

import lombok.Data;
@Data
public class SaEmpInfo {
    private String cdEmp;               // 사원코드
    private String mn111;               // 중소기업 취업감면율
    private String daEnter;             // 입사일자
    private String ynMateDed;           // 배우자공제
    private String num20Family;         // 20세
    private String num60Family;         // 60세
    private String numManyFamily;       // 다자녀
    private String ynResident;          // 거주구분
    private String ynUnit;              // 생산직여부
    private String ynForLabor;          // 해외
    private String ynOverwork;          // 연장근로비과세
    private String daRetire;            // 퇴사날짜
    private String cdOccup;             // 직종
    private String rankNo;              // 직급
    private String cdDept;              // 부서
    private String nmDept;              // 부서이름
    private String cdField;             // 현장
    private String cdProject;           // 프로젝트

    private String amNationPen;         // 국민연금보험료
    private String amHealthInsur;       // 건강보험료
    private String mn211;               // 두루누리 적용률
    private String noSocial;            // 주민번호
    private String MnReduction;         // 감면율(임시 칼럼)

    private String workday;             // 귀속월 근무일수
    private String overTime;            // 초과근무 시간
    private String hourlywage;          // 통상임금
    private String jobOk;               // 퇴사여부

    private String nmEmp;               // 사원이름
    private String num2060many;         // 20/60/다자녀
    private String ynUnitForlabor;      // 생산/국외
    private String allowMonthWorktime;  // 귀속월 총 근무시간


    /*  검색 파라미터 */
    private String allowMonth;          // 귀속년월
    private String salDivision;         // 구분
    private String paymentDate;         // 지급일

    private String searchCdEmp;		    // 검색 사원코드
    private String searchCdDept;		// 검색 부서코드
    private String searchRankNo;		// 검색 직급코드
    private String searchCdOccup;		// 검색 직책코드
    private String searchCdField;		// 검색 현장코드
    private String searchCdProject;	    // 검색 프로젝트코드
    private String searchYnUnit;		// 검색 생산직 여부
    private String searchYnForlabor;	// 검색 국외근로여부
}
