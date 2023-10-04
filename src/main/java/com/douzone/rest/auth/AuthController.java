package com.douzone.rest.auth;

import com.douzone.rest.auth.mail.EmailService;
import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.company.service.CompanyService;
import com.douzone.rest.company.vo.DataSourceVo;
import com.douzone.rest.datasource.DataSourceConfig;
import com.douzone.rest.datasource.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://osh8242.iptime.org"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    //    @CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "Authorization")
    @PostMapping("/login")
    public ResponseEntity<ResponseVo> login(@RequestBody UserVo user, HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        System.out.println("clientIP = " + clientIP);
        System.out.println("parameter login info: ");
        System.out.println(user);
        ResponseVo response = authService.findUser(user);
               if (response.getMessage().equals("SUCCESS")) {
            HttpHeaders headers = new HttpHeaders();
            // ResponseVo에서 토큰을 가져와서 헤더에 추가
            System.out.println("responseeeeeeeeeee login");
            System.out.println(response);

            System.out.println(response.getToken());
            System.out.println("succeess token: "+response.getToken());
            headers.set("Authorization", "Bearer " + response.getToken());
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

        @PostMapping("/cookieLogin")
        @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
        public ResponseEntity<ResponseVo> cookieLogin(@RequestBody UserVo user, HttpServletResponse httpResponse) {
            System.out.println("d---------------------------d");
            ResponseVo response = authService.findUser(user);

            if (response.getMessage().equals("SUCCESS")) {
                System.out.println("SUCCCEESSS COKIIIEEE");
                Cookie tokenCookie = new Cookie("authToken", response.getToken());
                tokenCookie.setPath("/");
                tokenCookie.setMaxAge(7 * 24 * 60 * 60); // 1주일

                httpResponse.addCookie(tokenCookie);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        }

    @PostMapping("/register")
    public String Register(@RequestBody UserVo user) throws Exception {
        String password = user.getUserPwd();
        System.out.println("Register Parameter: " + user);
        // 스키마 관리자 계정 등록
        int resultMsg = authService.register(user);
        System.out.println("result Msg: " + resultMsg);
        Integer schemaResult = null;
        if (resultMsg == 1) {
            String companyCode = user.getCompanyCode();
            // 스키마 생성
            schemaResult = companyService.createNewSchema(companyCode, password);
            if (schemaResult == 1) {
                // 데이터소스 DB에 등록
                int datasourceResult = dataSourceService.insertDataSourceVo(new DataSourceVo(companyCode, password));
                if (datasourceResult == 1) {
                    // 데이터소스 서버에 등록
                    dataSourceConfig.addNewDataSource(companyCode, password);
                    return "SUCCESS";
                }
                else return "INSERT DATASOURCE FAIL";
            } else return "CREATE SCHEMA FAIL";
        } else return "FAIL";
    }

    @PostMapping("/checkVaildCd")
    public String checkVaildCd(@RequestBody UserVo user) {
        String result = "";
        System.out.println("check id consripll parm ; " + user);
        int checkIdResult = authService.checkValidCd(user);
        if (checkIdResult == 1)
            result = "SUCCESS";
        else result = "FAIL";
        return result;
    }


    @PostMapping("/checkVaildId")
    public String checkVaildId(@RequestBody UserVo user) {
        String result = "";
        System.out.println("check id consripll parm ; " + user);
        int checkIdResult = authService.checkValidId(user);
        if (checkIdResult == 1)
            result = "SUCCESS";
        else result = "FAIL";
        return result;
    }

    @PostMapping("/checkVaildEmail")
    public String checkVaildEmail(@RequestBody UserVo user) {
        String result = "";
        System.out.println("check id consripll parm ; " + user);
        int checkIdResult = authService.checkValidEmail(user);
        if (checkIdResult == 1)
            result = "SUCCESS";
        else result = "FAIL";
        return result;
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/findEmail")
    public ResponseEntity<ResponseVo> findEmail(@RequestBody UserVo user) {
        System.out.println("parameter email info: ");
        System.out.println(user);
//        UserVo u = new UserVo("1", "2", "3", "seoyeonev@gmail.com");
//        ResponseVo response = new ResponseVo();
        // TODO : 동일 이메일 있을 경우 , 이메일 보내기

        UserVo result = authService.IdByEmail(user);
        System.out.println("reseeeitt emailllld");
        System.out.println(result);

        // TODO : send Email Sevice 구현
//        System.out.println("Sending email to: " + "llikepsh515@gmail.com");
//        if (user != null && user.getUserEmail() != null) {
        emailService.sendSimpleMessage(
                user.getEmail(),
                "[무한더존] 요청하신 아이디를 발송해 드립니다.",
                "\n요청하신 아이디를 발송해 드립니다.\n ID: " + result.getUserId()
        );
//        }

        ResponseVo response = new ResponseVo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
