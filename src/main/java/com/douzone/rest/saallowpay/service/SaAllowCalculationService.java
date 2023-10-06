package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllow;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaAllowCalculationService {
    private final SaAllowPayMapper saAllowPayMapper;
    private final SaDeductCalculationService saDeductCalculationService;

    private String cdEmp;
    private String dateId;
    private String cdAllow;
    private String allowMonth;

    private String allowYear;
    private String salDivision;

    // 상수 정의
    private static final String TAXABLE = "Y";
    private static final String NON_TAXABLE = "N";

    @Autowired
    public SaAllowCalculationService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductCalculationService saDeductCalculationService
    ) {

        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductCalculationService = saDeductCalculationService;
    }


    // 직접 입력한 급여항목 insert or update
    public int mergeNewSalaryAllowPay(SaAllowPay saAllowPay) throws Exception {

        this.dateId = saAllowPay.getDateId();
        this.cdEmp = saAllowPay.getCdEmp();
        this.cdAllow = saAllowPay.getCdAllow();
        this.salDivision = saAllowPay.getSalDivision();

        int result = 0;
        try {
            List<SaAllowPay> newSalaryAllowPayList = makeCalculationAllowPayData(saAllowPay);   // 수당 리스트

            saAllowPayMapper.deleteSalAllowPay(saAllowPay); // 수당 delete
            result = saAllowPayMapper.mergeSalAllowPay(newSalaryAllowPayList);   // 수당 insert or update

        }catch (Exception e) {
            e.getStackTrace();
            System.out.println("newSalaryAllowPay에서 터짐");
        }
        return result;
    }

    // 재계산 버튼 함수
    public int getReCalculateResult(Map<String, Object> requestMap) {

        int result = 0;
        this.cdEmp = (String) requestMap.get("cdEmp");
        this.dateId = requestMap.get("dateId").toString();;
        this.allowMonth = (String) requestMap.get("allowMonth");
        this.allowYear = (String) requestMap.get("allowYear");
        this.salDivision = (String) requestMap.get("salDivision");

        List<String> selectOptionList = (List<String>) requestMap.get("selectOption");

        for (String option : selectOptionList) {
            switch (option) {
                case "editEmpInfo":               // 사원정보 변경 진 메뉴 생산직여부
                case "recalculateTaxYn":          // 과세 비과세 재계산
                    result = recalculateTaxYn();
                    break;
                case "recalculateDeductInfo":       // 공제항목 재계산
                    result = recalculateDeductInfo();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    // 과세 비과세 재계산
    private int recalculateTaxYn() {
        System.out.println("####과세 비과세 재계산####");
        int result = 0;

        try {
            // 선택한 지급일의 지급내역 다시 불러오기
            SaAllowPay salAllowPay = new SaAllowPay();
            salAllowPay.setCdEmp(this.cdEmp);
            salAllowPay.setDateId(this.dateId);
            salAllowPay.setSalDivision(this.salDivision);

            List<SaAllowPay> reCalculateAllowPayList = new ArrayList<>();

            if(getSalAllowPayList(salAllowPay)!=null) {
                for (SaAllowPay saAllowPay : getSalAllowPayList(salAllowPay)) {
                    for (SaAllowPay reCalculateAllowPay : makeCalculationAllowPayData(saAllowPay)) {
                        reCalculateAllowPayList.add(reCalculateAllowPay);
                    }
                }

                // 해당날짜의 과세 비과세 재계산 내역 update
                result = saAllowPayMapper.mergeSalAllowPay(reCalculateAllowPayList);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("! reCalculateSalaryPayment 에서 터짐");
        }

        return result;
    }

    //  공제항목 재계산
    private int recalculateDeductInfo() {
        System.out.println("####공제항목 재계산####");

        int result = 0;
        try {
            SaAllowPay salAllowPay = new SaAllowPay();
            salAllowPay.setCdEmp(this.cdEmp);
            salAllowPay.setDateId(this.dateId);

            saDeductCalculationService.mergeNewDeductAllowPay(salAllowPay);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 과세 비과세 처리
    private List<SaAllowPay> makeCalculationAllowPayData(SaAllowPay saAllowPay){

        List<SaAllowPay> salaryAllowList = new ArrayList<>();

        try {
            SaAllow salAllowInfo = getSalAllowInfo(saAllowPay); // 지급항목의 정보

            if (NON_TAXABLE.equals(salAllowInfo.getYnTax())) {

                System.out.println("delete 지급항목들");

                deleteSalAllowPay(saAllowPay);  // 해당 사원의 해당날짜 모든 지급항목 삭제

                int allowPay = Integer.parseInt(saAllowPay.getAllowPay());      // 입력한 수당 값

                if (salAllowInfo.getNonTaxLimit() == null) {
                    salaryAllowList.add(createSalAllowPay(saAllowPay.getCdAllow(), NON_TAXABLE, String.valueOf(allowPay)));
                } else {
                    int limit = Integer.parseInt(salAllowInfo.getNonTaxLimit());   // 한달 한도
                    int allowPaySumByEmp = getSalAllowPaySumByMonth(saAllowPay);   // 사원이 이번달 받은 해당 수당의 합

                    if (allowPaySumByEmp + allowPay > limit) {
                        salaryAllowList.add(createSalAllowPay(saAllowPay.getCdAllow(),NON_TAXABLE, String.valueOf(limit - allowPaySumByEmp)));
                        salaryAllowList.add(createSalAllowPay(saAllowPay.getCdAllow(),TAXABLE, String.valueOf(allowPay - (limit - allowPaySumByEmp))));

                    } else {
                        salaryAllowList.add(createSalAllowPay(saAllowPay.getCdAllow(),NON_TAXABLE, String.valueOf(allowPay)));
                    }
                }

            } else {
                salaryAllowList.add(createSalAllowPay(saAllowPay.getCdAllow(),TAXABLE, saAllowPay.getAllowPay()));
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("makeCalculationAllowPayData에서 터짐");
        }
        return salaryAllowList;
    }

    // 사원의 귀속연월의 해당 수당항목의 합
    private int getSalAllowPaySumByMonth(SaAllowPay saAllowPay){
        int result = 0;

        try {
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put("cdAllow", saAllowPay.getCdAllow());
            requestMap.put("allowMonth", saAllowPay.getAllowMonth());
            requestMap.put("cdEmp", saAllowPay.getCdEmp());

            if(saAllowPayMapper.getSumAllowPayByYnTax(requestMap) != null){
                result = saAllowPayMapper.getSalAllowPaySumByMonth(requestMap);
            };

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("getSalAllowPaySumByEmp에서 터짐");
        }

        return result;
    }

    // 입력/수정할 saAllowPay row 만들기
    private SaAllowPay createSalAllowPay(String cdAllow, String ynTax, String allowPay) {
        SaAllowPay newSaAllowPay = new SaAllowPay();
        try {
            newSaAllowPay.setCdAllow(cdAllow);
            newSaAllowPay.setCdEmp(this.cdEmp);
            newSaAllowPay.setDateId(this.dateId);
            newSaAllowPay.setSalDivision(this.salDivision);
            newSaAllowPay.setYnTax(ynTax);
            newSaAllowPay.setAllowPay(allowPay);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("createSalAllowPay에서 터짐...");
        }
        return newSaAllowPay;
    }

    // 해당 지급일의 사원의 지급내역 리스트 불러오기
    // 필수 값 : cdEmp, dateId
    private List<SaAllowPay> getSalAllowPayList(SaAllowPay saAllowPay) {
        List<SaAllowPay> resultList = null;
        try {
            Map<String,String> requestMap = new HashMap<>();

            requestMap.put("cdEmp", saAllowPay.getCdEmp());
            requestMap.put("dateId", saAllowPay.getDateId());
            requestMap.put("salDivision" , saAllowPay.getSalDivision());

            //List<SaAllowPay> a = saAllowPayMapper.getSalAlLowPayListByEmp(requestMap);
            resultList =  saAllowPayMapper.getSalAlLowPayListByEmpForCalculation(requestMap).stream()
                    .filter(s -> s.getCdEmp() != null)
                    .collect(Collectors.toList());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getSalAllowPayList에서 터짐.");
            return null;
        }
        return resultList;
    }
    
    // 해당 지급항목의 정보 불러오기
    // 필수 값 : cdAllow
    private SaAllow getSalAllowInfo(SaAllowPay saAllowPay) {
        try {
            System.out.println(saAllowPay.toString());
            return saAllowPayMapper.getSalAllowInfo(saAllowPay);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getSalAllowInfo에서 터짐");
            return null;
        }
    }

    // 해당 지급일의 사원의 지급내역 삭제하기
    private int deleteSalAllowPay(SaAllowPay saAllowPay) {
        int result = 0;
        try {
            result = saAllowPayMapper.deleteSalAllowPay(saAllowPay);
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("deleteSalAllowPay에서 터짐");
        }
        return result;
    }

}
