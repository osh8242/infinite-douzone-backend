package com.douzone.rest.saallowpay.service;

import com.douzone.rest.saallowpay.dao.SaAllowPayMapper;
import com.douzone.rest.saallowpay.vo.SaAllowPay;
import com.douzone.rest.sadeductpay.dao.SaDeductPayDao;
import com.douzone.rest.sadeductpay.vo.SaDeductPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SaDeductCalculationService {
    private final SaAllowPayMapper saAllowPayMapper;
    private final SaDeductPayDao saDeductPayDao;
    int incomePay = 0;

    @Autowired
    public SaDeductCalculationService(
            SaAllowPayMapper saAllowPayMapper,
            SaDeductPayDao saDeductPayDao
            ) {
        this.saAllowPayMapper = saAllowPayMapper;
        this.saDeductPayDao = saDeductPayDao;
    }

    public int mergeNewDeductAllowPay(SaAllowPay saAllowPay){
        int result = 0;
        try {
            List<SaDeductPay> newDeductPayList = makeCalculationDeductPayList(saAllowPay);
            result = saDeductPayDao.mergeSaDeductPayList(newDeductPayList);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    private List<SaDeductPay> makeCalculationDeductPayList(SaAllowPay saAllowPay){

        List<SaDeductPay> makeCalculationDeductPayData = new ArrayList<>();

        try{
            // 해당 dateId에 해당하는 해당귀속월의 사원별 급여유형(보너스or급여)별 과세항목의 합 가져오기
            Map<String,Integer> sumAllowPayByYnTax = saAllowPayMapper.getSalAllowPaySumTaxY(saAllowPay);
            int sumTaxYBySal = Integer.parseInt(String.valueOf(sumAllowPayByYnTax.get("sumTaxYBySal")));
            int sumTaxYByBonus = Integer.parseInt(String.valueOf(sumAllowPayByYnTax.get("sumTaxYByBonus")));

            // 공제항목 정보 가져오기
            Map<String,String> requestMap = new HashMap<>();

            List<Map<String, String>> getsalDeductList = saDeductPayDao.getsalDeductList(requestMap);

            for(Map<String, String> salDeduct : getsalDeductList){
                SaDeductPay newDeductPay = new SaDeductPay();
                newDeductPay.setCdDeduct(salDeduct.get("cdDeduct"));
                newDeductPay.setCdEmp(saAllowPay.getCdEmp());
                newDeductPay.setDateId(saAllowPay.getDateId());

                int allowpaySum = 0;
                if("Y".equals(salDeduct.get("ynBonus"))) allowpaySum += sumTaxYByBonus;
                if("Y".equals(salDeduct.get("ynSal"))) allowpaySum += sumTaxYBySal;

                int deductPay = 0;
                if ("DEDUCT_INCOME".equals(salDeduct.get("cdDeduct")) || "DEDUCT_LOCALINCOME".equals(salDeduct.get("cdDeduct"))) {
                    deductPay = makeDeductPay(salDeduct.get("cdDeduct") , allowpaySum, 0.0);
                }else{
                    deductPay = makeDeductPay(salDeduct.get("cdDeduct") , allowpaySum, Double.parseDouble(salDeduct.get("rate")));
                }

                newDeductPay.setAllowPay(Integer.toString(deductPay));
                makeCalculationDeductPayData.add(newDeductPay);
            }

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("makeCalculationDeductPayData에서 터짐");
        }
        return makeCalculationDeductPayData;
    }

    private int makeDeductPay(String cdDeduct, int allowPaySum, double rate) {
        int deductPay = 0;

        try {
            if (cdDeduct.equals("DEDUCT_INCOME")) {
                if (allowPaySum * 12 > 500000000) {
                    deductPay = (int)(allowPaySum * 0.168);
                } else if (allowPaySum * 12 > 300000000){
                    deductPay = (int)(allowPaySum * 0.280032);
                } else if (allowPaySum * 12 > 150000000) {
                    deductPay = (int)(allowPaySum * 0.191192);
                } else if (allowPaySum * 12 > 88000000) {
                    deductPay = (int)(allowPaySum * 0.1098109);
                } else if (allowPaySum * 12 > 50000000) {
                    deductPay = (int)(allowPaySum * 0.052);
                } else if (allowPaySum * 12 >= 12720000) {
                    deductPay = (int)(allowPaySum * 0.014);
                } else {
                    deductPay = 0;
                }

                incomePay = deductPay;

        } else if (cdDeduct.equals("DEDUCT_LOCALINCOME")) {   // 지방소득세 = 소득세 * 0.1
            deductPay = (int)(incomePay * 0.1);
        } else {
            deductPay = (int) (allowPaySum * (rate / 100)); // 기타 비율만 곱하기
        }

        }catch (Exception e){
            e.getStackTrace();
            System.out.println("makeDeductPay 에서 터짐");
        }
        return deductPay;
    }

}
