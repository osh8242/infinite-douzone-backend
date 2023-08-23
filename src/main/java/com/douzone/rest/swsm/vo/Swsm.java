package com.douzone.rest.swsm.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Swsm {
    String incomeClassfication;
    String withholdingYear;
    String dateOfCreation;
    String empCode;
    String otherBenefitsItem;
    String otherBenefitsAmount;
    String name;
    String residentState;
    String rrn;
    String salaryAmount;
    String bonusAmount;
    String startEmpContractPeriod;
    String endEmpContractPeriod;
    String postCode;
    String address;
    String addDetail;
    String jobDescription;
    String startBreakTime;
    String endBreakTime;
    String workingDay;
    String dayOff;
    String salaryType;
    String otherBenefits;
    String bonusPaymentStatus;
    String salaryPaymentDateType;
    String paymentDate;
    String paymentMethod;
    String empInsurance;
    String compensationInsurance;
    String nationalPension;
    String healthInsurance;
}
