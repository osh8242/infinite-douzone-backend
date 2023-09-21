package com.douzone.rest.auth;

import com.douzone.rest.auth.jwt.JwtProperties;
import com.douzone.rest.auth.mail.EmailService;
import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public String login(@RequestBody UserVo user, HttpServletResponse response) {
        System.out.println("parameter login info: ");
        System.out.println(user);
        ResponseVo responseVo = authService.findUser(user);
        System.out.println("responseVo.getToken() = " + responseVo.getToken());
        response.addHeader(JwtProperties.HEADER_STRING, responseVo.getToken());
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER_STRING);

        return responseVo.getMessage();
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/findEmail")
    public ResponseEntity<ResponseVo> findEmail(@RequestBody UserVo user) {
        System.out.println("parameter email info: ");
        System.out.println(user);  // UserVo 객체에 getEmail 메서드가 있어야 합니다.
//        UserVo u = new UserVo("1", "2", "3", "seoyeonev@gmail.com");
//        ResponseVo response = new ResponseVo();
        // TODO : 동일 이메일 있을 경우 , 이메일 보내기

        // TODO : send Email Sevice 구현
        System.out.println("Sending email to: " + "llikepsh515@gmail.com");
//        if (user != null && user.getUserEmail() != null) {
            emailService.sendSimpleMessage(
//                    "llikepsh515@gmail.com".trim(),
                    "llikepsh515@gmail.com",
                    "Testing sendEmail",
                    "hi im seoyeonlee hehe"
            );
//        }

        ResponseVo response = new ResponseVo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/findEmail")
//    public ResponseEntity<ResponseVo> findEmail(@RequestBody UserVo user) {
//        System.out.println("parameter email info: " + user);
//
//        // TODO : 요청 데이터와 일치하는 이메일 찾는 로직 구현하기...
//        // TODO : send Email Sevice 구현
//        if (user != null && user.getUserEmail() != null) {
//            emailService.sendSimpleMessage(
//                    user.getUserEmail(),
//                    "Your Subject Here",
//                    "Your email body here..."
//            );
//        }
//
//        ResponseVo response = new ResponseVo();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


//        if ("SUCCESS".equals(response.getMessage())) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);

//    @PostMapping("/login")
//    public int login(@RequestParam String userId, @RequestParam String userPwd) {
//        System.out.println("parameter login info: userId=" + userId + ", userPwd=" + userPwd);
//        UserVo user = new UserVo();
//        user.setUserId(userId);
//        user.setUserPwd(userPwd);
//        int result = authService.findUser(user);
//        return 0;
//    }


//        public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
//            User user = userRepository.findByUsername(loginRequest.getUsername());
//            if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
//                // 로그인 성공
//                return ResponseEntity.ok(new LoginResponse("로그인 성공"));
//            } else {
//                // 로그인 실패
//                return ResponseEntity.badRequest().body(new LoginResponse("로그인 실패"));
//            }
//        }
//    }

}
