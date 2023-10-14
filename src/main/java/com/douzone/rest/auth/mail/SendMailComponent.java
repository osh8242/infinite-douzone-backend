package com.douzone.rest.auth.mail;

import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.swsm.vo.Swsm;
import com.douzone.rest.swsm.vo.SwsmOther;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMailComponent {

    private final EmailService emailService;

    public SendMailComponent(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendTableForm(Swsm swsm, List<SwsmOther> mailSwsmOtherList, UserVo user) {

        String dayoff = "";
        String salType = "";

        switch (swsm.getDayOff()) {
            case "mon":
                dayoff = "월";
                break;
            case "tue":
                dayoff = "화";
                break;
            case "wed":
                dayoff = "수";
                break;
            case "thu":
                dayoff = "목";
                break;
            case "fri":
                dayoff = "금";
                break;
            case "sat":
                dayoff = "토";
                break;
            case "sun":
                dayoff = "일";
                break;
            default:
                dayoff = "토";
                break;
        }

        switch (swsm.getSalaryType()) {
            case "monthlySal":
                salType = "월급";
                break;
            case "dailySal":
                salType = "일급";
                break;
            case "hourlySal":
                salType = "시급";
                break;
        }

        String ei = "X";
        String ci = "X";
        String np="X";
        String hi="X";
        if (swsm.getEmpInsurance().equals("T")) {
            ei = "O";
        }
        if (swsm.getCompensationInsurance().equals("T")) {
            ci = "O";
        }
        if(swsm.getNationalPension().equals("T")){
            np="O";
        }
        if(swsm.getHealthInsurance().equals("T")){
            hi="O";
        }

        String otherList="";

        for(int i=1; i<mailSwsmOtherList.size()+1; i++ ){
            otherList+=
                    "<tr>\n" +
                    "<td style=\"width: 144px; height: 21px; text-align: right;\">"+i+")&nbsp;</td>\n" +
                    "<td style=\"width: 230px; height: 21px;\">"+mailSwsmOtherList.get(i-1).getOtherType()+" :&nbsp;</td>\n" +
                    "<td style=\"width: 184.266px; height: 21px; text-align: right;\">"+mailSwsmOtherList.get(i-1).getOtherMoney()+"&nbsp;</td>\n" +
                    "<td style=\"width: 503.734px; height: 21px;\">원</td>\n"+
            "</tr>\n";
        }



        String to = "seoyeonev@gmail.com";
        String subject = "[" + user.getCompanyName() + "] 근로계약서 송부";
        String html = "<div style=\"border: 2px solid #000; width: 794px; height: 1123px; padding-left: 40px;\">\n" +
                "<h2 style=\"text-align: center; margin-top: 50px; margin-right: 50px;\"><span style=\"color: #000000; font-size: 30px;\">표 준 근 로 계 약&nbsp;</span></h2>\n" +
                "  <p>&nbsp;</p>\n" +
                "<p>&nbsp;" + user.getCompanyName() + "(이하 '사업주'라 함)과(와) " + swsm.getNmKrname() + "(이하 '근로자'라 함)은 다음과 같이 근로계약을 체결한다.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<table style=\"width: 602px;\">\n" +
                "<tbody>\n" +
                "<tr style=\"height: 30px;\">\n" +
                "<td style=\"width: 65px; height: 30px; text-align: right;\">1.</td>\n" +
                "<td style=\"width: 350px; height: 30px; text-align: right;\">근&nbsp; 로&nbsp; 게&nbsp; 시&nbsp; 일 :&nbsp;</td>\n" + swsm.getStartEmpContractPeriod() + "~" + swsm.getEndEmpContractPeriod() +
                "<td style=\"width: 290.823px; height: 30px; text-align: right;\" colspan=\"2\">&nbsp; &nbsp;&nbsp;</td>\n" +
//                "<td style=\"width: 706.177px; height: 30px;\">&nbsp;~ "+swsm.getEndEmpContractPeriod()+"</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 28px;\">\n" +
                "<td style=\"width: 65px; height: 28px; text-align: right;\">2.</td>\n" +
                "<td style=\"width: 350px; height: 28px; text-align: right;\">근&nbsp; &nbsp; 무&nbsp; &nbsp; 장&nbsp; &nbsp;소 :&nbsp;</td>\n" +
                "<td style=\"width: 1000px; height: 28px; text-align: left;\" colspan=\"3\">&nbsp;" + swsm.getAddress() + ", " + swsm.getAddDetail() + "</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 4px;\">\n" +
                "<td style=\"width: 65px; height: 4px; text-align: right;\">\n" +
                "<p>3.</p>\n" +
                "</td>\n" +
                "<td style=\"width: 350px; height: 4px; text-align: right;\">업&nbsp; 무&nbsp; 의&nbsp; &nbsp;내 용 :&nbsp;</td>\n" +
                "<td style=\"width: 1000px; height: 4px; text-align: left;\" colspan=\"3\">&nbsp;" + swsm.getJobDescription() + "</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 29px;\">\n" +
                "<td style=\"width: 65px; height: 29px; text-align: right;\">4.</td>\n" +
                "<td style=\"width: 350px; height: 29px; text-align: right;\">소 정 근 로 시 간 :&nbsp;</td>\n" +
                "<td style=\"width: 1000px; height: 29px; text-align: left;\" colspan=\"3\">&nbsp;8 시간</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 32px;\">\n" +
                "<td style=\"width: 65px; height: 32px; text-align: right;\">5.</td>\n" +
                "<td style=\"width: 350px; height: 32px; text-align: right;\">&nbsp;근 무 일&nbsp; /&nbsp; 휴 일 :&nbsp;</td>\n" +
                "<td style=\"width: 280px; height: 32px; text-align: left;\">" + swsm.getWorkingDay() + "일 근무</td>\n" +
//                "<td style=\"width: 706.177px; height: 32px; text-align: left;\">주휴일 :&nbsp; 매주 요일&nbsp;&nbsp;</td>+ "
                "<td style=\"width: 550.328px; height: 32px;\"> 매 주 " + dayoff + "요일&nbsp;&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p>&nbsp; &nbsp; &nbsp;6.&nbsp; 임&nbsp; &nbsp; 금</p>\n" +
                "<p>&nbsp; &nbsp; &nbsp; &nbsp; - " + salType + " :&nbsp;" + swsm.getSalaryAmount() + "원</p>\n" +
                "<p>&nbsp; &nbsp; &nbsp; &nbsp; - 상여금 : " + swsm.getBonusAmount() + "원</p>\n" +
                "<p>&nbsp; &nbsp; &nbsp; &nbsp; - 기타급여&nbsp;</p>\n" +
                "<table style=\"width: 536px; float: left;\">\n" +
                "<tbody>\n" +
//                "<tr style=\"height: 21px;\">\n" +
                otherList+
//                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\">&nbsp;<br></p>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; 7. 임금지급일 : 매 월 25일 (휴일의 경우는 전일 지급)</p>\n" +
                "<p style=\"text-align: left;\">&nbsp;&nbsp; &nbsp; &nbsp; 8. 사회보험 적용 여부</p>\n" +
                "<table style=\"width: 563px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 119px;\">&nbsp;</td>\n" +
                "<td style=\"width: 58px;\">"+ei+"</td>\n" +
                "<td style=\"width: 239px;\">고용보험</td>\n" +
                "<td style=\"width: 78px;\">&nbsp;</td>\n" +
                "<td style=\"width: 83px;\">"+ci+"</td>\n" +
                "<td style=\"width: 375px;\">산재보험</td>\n" +
                "<td style=\"width: 87px;\">"+np+"</td>\n" +
                "<td style=\"width: 284px;\">국민연금</td>\n" +
                "<td style=\"width: 116px;\">&nbsp;</td>\n" +
                "<td style=\"width: 98px;\">"+hi+"</td>\n" +
                "<td style=\"width: 707px;\">건강보험</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp;&nbsp;</p>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; 9. 근로계약서 교부 (근로기준법 제17조 이행)</p>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp; - 사업주는 근로계약을 체결함과 동시에 본 계약서를 사본하여 근로자의 교부와 관계없이 근로자에게 교부함</p>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp;10. 근로계약, 취업규칙 등의 성실한 이행의무</p>\n" +
                "<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp; - 사업주와 근로자는 각자 근로계약 작업규칙, 단체협약을 지키고 성실하게 이행하여야 함&nbsp;</p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">"+swsm.getPaymentDate()+"</p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: right;\">사 업 체 명 :     "+user.getCompanyName()+" (인)&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\n" +
                "<p style=\"text-align: right;\">근 로 자 명 :     "+ swsm.getNmKrname()+" (인)&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\n" +
                "</div>";

        emailService.sendSwsmTable(to, subject, html);

    }
}
