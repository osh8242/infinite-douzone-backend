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

@Service
public class SaAllowCalculationService {
    private final SaAllowPayMapper saAllowPayMapper;
    private String cdEmp;
    private String dateId;
    private String cdAllow;
    private String allowMonth;

    private String allowYear;

    // 상수 정의
    private static final String TAXABLE = "Y";
    private static final String NON_TAXABLE = "N";

    @Autowired
    public SaAllowCalculationService(SaAllowPayMapper saAllowPayMapper) {
        this.saAllowPayMapper = saAllowPayMapper;
    }

    // 직접 입력한 급여항목 insert or update
    public int mergeNewSalaryAllowPay(SaAllowPay saAllowPay) {

        this.dateId = saAllowPay.getDateId();
        this.cdEmp = saAllowPay.getCdEmp();
        this.cdAllow = saAllowPay.getCdAllow();

        int result = 0;
        try {

            if("".equals(dateId)) {}//dateId 맹들기
            List<SaAllowPay> newSalaryAllowPayList = makeCalculationAllowPayData(saAllowPay);   // 수당 리스트
            saAllowPayMapper.mergeSalAllowPay(newSalaryAllowPayList);   // 수당 insert or update

            mergeNewSalaryDeductPay();

        }catch (Exception e) {
            e.getStackTrace();
            System.out.println("newSalaryAllowPay에서 터짐");
        }
        return result;
    }

    // 입력한 급여항목에 대한 공제항목 계산
     public int mergeNewSalaryDeductPay() {

         int result = 0;
         try {
             // 국민연금

         } catch (Exception e) {
             e.getStackTrace();
             System.out.println("mergeNewSalaryDeductPay에서 터짐");
         }
         return result;
     }

    // 재계산 버튼 함수
    public int getReCalculateResult(Map<String, Object> requestMap) {

        int result = 0;
        this.cdEmp = (String) requestMap.get("cdEmp");
        this.dateId = (String) requestMap.get("dateId");
        this.allowMonth = (String) requestMap.get("allowMonth");
        this.allowYear = (String) requestMap.get("allowYear");

        List<String> selectOptionList = (List<String>) requestMap.get("selectOption");

        for (String option : selectOptionList) {
            switch (option) {
                case "calculateHourlyWage" :  reCalculateHourlyWage();  // 통상시급 재계산
                case "editEmpInfo":         // 사원정보 변경 진 메뉴 생산직여부, 4대보험 여부 계산
                case "calculateTaxYn":      // 과세 비과세 재계산
                    result = reCalculateSalaryPayment();
                    break;
                case "incomeTax":           // 소득세 재계산
                    result = reCalculateIncomeTax();
                    break;
                case "nationalPensionChange" :   // 4대보험만 변경
                    result = reCalculateInsuranceChange();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    // 4대보험 변경사항 재계산
    private int reCalculateInsuranceChange(){
        int result = 0;
        try {
            System.out.println("4대보험 변경사항 재계산 구현중..."); // 4대보험 변경사항만 재계산
            // nationalPension 서연언니 국민연금 여부 필드

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("reCalculateInsuranceChange에서 재계산됨");
        }
        return result;
    }

    // 과세 비과세 재계산
    private int reCalculateSalaryPayment() {
        System.out.println("과세 비과세 재계산, 사원정보 변경 재계산, 통상시급 계산");
        int result = 0;

        try {
            // 선택한 지급일의 내역 다시 불러오기
            SaAllowPay salAllowPay = new SaAllowPay();
            salAllowPay.setCdEmp(this.cdEmp);
            salAllowPay.setDateId(this.dateId);

            List<SaAllowPay> reCalculateAllowPayList = new ArrayList<>();

            for (SaAllowPay saAllowPay : getSalAllowPayList(salAllowPay)){
                for (SaAllowPay reCalculateAllowPay : makeCalculationAllowPayData(saAllowPay)){
                    reCalculateAllowPayList.add(reCalculateAllowPay);
                }
            }

            // 해당날짜의 과세 비과세 재계산 내역 udpate하기
            result = saAllowPayMapper.mergeSalAllowPay(reCalculateAllowPayList);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("! reCalculateSalaryPayment 에서 터짐");
        }

        return result;
    }

    // 소득세 재계산 - 전월데이터 복사때문에 생긴 메뉴로 추청... 기준은 간이세액 )
    private int reCalculateIncomeTax() {
        System.out.println("소득세 재계산");

        int result = 0;
        try {
            System.out.println("소득세 재계산 구현중...");

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
                deleteSalAllowPay(saAllowPay);  // 해당 사원의 해당날짜 모든 지급항목 삭제

                int limit = Integer.parseInt(salAllowInfo.getNonTaxLimit()); // 한달 한도

//                int allowPaySumByEmp =  ();
                int allowPay = Integer.parseInt(saAllowPay.getAllowPay());

                if (allowPay > limit) {
                    salaryAllowList.add(createSalAllowPay(NON_TAXABLE, String.valueOf(limit)));
                    salaryAllowList.add(createSalAllowPay(TAXABLE, String.valueOf(allowPay - limit)));
                } else {
                    salaryAllowList.add(createSalAllowPay(NON_TAXABLE, String.valueOf(allowPay)));
                }
            } else {
                salaryAllowList.add(createSalAllowPay(TAXABLE, saAllowPay.getAllowPay()));
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("makeCalculationAllowPayData에서 터짐");
        }
        return salaryAllowList;
    }

    //
    private int getSalAllowPaySumByEmp() {
        int result = 0;
        try {

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("getSalAllowPaySumByEmp에서 터짐");
        }
        return result;
    }

    // 통상시급 재계산
    private int reCalculateHourlyWage() {
        System.out.println("통상시급 재계산");

        int result = 0;
        try {
            // 통상시급 = 통상포함 선택된 수당 / 정기근로시간 (월 소정 근로시간 = 209시간)
            Map<String,String> requestMap = new HashMap<>();
            requestMap.put("allowYear", this.allowYear);
            requestMap.put("cdEmp", this.cdEmp);

            // 통상임금 update 시켜주기
            result = saAllowPayMapper.updateHourlyWage(requestMap);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("reCalculateHourlyWage()에서 터짐");
        }
        return result;
    }

    // 입력/수정할 saAllowPay row 만들기
    private SaAllowPay createSalAllowPay(String ynTax, String allowPay) {
        SaAllowPay newSaAllowPay = new SaAllowPay();
        try {
            newSaAllowPay.setCdAllow(this.cdAllow);
            newSaAllowPay.setCdEmp(this.cdEmp);
            newSaAllowPay.setDateId(this.dateId);
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
        try {
            Map<String,String> requestMap = new HashMap<>();
            requestMap.put("cdEmp", saAllowPay.getCdEmp());
            requestMap.put("dateId", saAllowPay.getDateId());
            return saAllowPayMapper.getSalAlLowPayListByEmp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getSalAllowPayList에서 터짐.");
            return null;
        }
    }
    
    // 해당 지급항목의 정보 불러오기
    // 필수 값 : cdAllow
    private SaAllow getSalAllowInfo(SaAllowPay saAllowPay) {
        try {
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

    // 해당 귀속월의 해당 급여항목의 총액 구해오기
    private int getTotalAllowPayByCdAllow(SaAllowPay saAllowPay){
        int totalPay = 0;
        try {
            //totalPay = saAllowPayMapper.getTotalAllowPayByCdAllow(saAllowPay);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getTotalAllowPayByCdAllow에서 터짐");
        }
        return totalPay;
    }


    // DateId 만들기
    // Date테이블 dateId 생성하는 프로시져 호출
    // requestMap (allowyear, allowMonth, paymentDate)
    private void setDateId(Map<String,String> requestMap){
        try {
            setDateId(requestMap);
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("setDateId에서 터짐.");
        }
    }
}
