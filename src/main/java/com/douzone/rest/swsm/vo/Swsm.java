package com.douzone.rest.swsm.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Swsm {
    String withholdingYear; // "원천년도"
    String dateOfCreation; // "작성년월"
    String incomeClassfication; // "소득구분"
    String cdEmp; // "사원코드"
    String nmKrname; // "성명"
    String residentState; // "거주구분"
    String noSocial; // "주민번호"
    String startEmpContractPeriod; // "시작근로계약기간"
    String endEmpContractPeriod; // "종료근로계약기간"
    String postCode; // "우편번호"
    String address; // "주소"
    String addDetail; // "상세주소"
    String jobDescription; // "업무의 내용"
    String startWorktime; // 시작근로시간
    String endWorktime; // 종료근로시간
    String startBreakTime; // "시작휴게시간"
    String endBreakTime; // "종료휴게시간"
    String workingDay; // "근무일"
    String dayOff; // "주휴일"
    String salaryType; // "임금유형" -- 임금
    String salaryAmount; // "임금액"
    String otherBenefits; // "기타급여"
    String otherBenefitsItem; // "기타급여항목"
    String otherBenefitsAmount; // "기타급여금액"
    String bonusPaymentStatus; // "상여금"
    String bonusAmount; // "상여금액"
    String salaryPaymentDateType; // "임금지급일"
    String paymentDate; // "임금지급날짜"
    String paymentMethod; // "지급방법"
    String empInsurance; // "고용보험"
    String compensationInsurance; // "산재보험"
    String nationalPension; // "국민연금"
    String healthInsurance; // "건강보험"
}