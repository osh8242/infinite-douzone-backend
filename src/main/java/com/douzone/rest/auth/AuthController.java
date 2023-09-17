package com.douzone.rest.auth;

import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
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

//    public AuthController(){
//        System.out.println("controollll");
//    }


    @PostMapping("/login")
    public ResponseEntity<ResponseVo> login(@RequestBody UserVo user) {
        System.out.println("parameter login info: " + user);
        ResponseVo response = authService.findUser(user);

        if(response.getMessage().equals("SUCCESS")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
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
