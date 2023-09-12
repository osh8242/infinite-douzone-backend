package com.douzone.rest.Auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

//        @Autowired
//        private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println("parameter login info: " + user);
        return null;
    }
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
