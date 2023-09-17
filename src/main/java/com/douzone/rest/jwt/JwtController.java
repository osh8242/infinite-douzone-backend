//package com.douzone.rest.jwt;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import static com.douzone.rest.jwt.JwtProperties.*;
//
//// 테스트용
//@RestController
//@RequestMapping("/jwt")
//public class JwtController {
//    private final JwtService jwtService;
//    @Autowired
//    public JwtController(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @GetMapping("/getToken")
//    public void getToken(@RequestParam("username") String username, HttpServletResponse response){
//        System.out.println("JwtController.getToken");
//        String token = TOKEN_PREFIX + jwtService.createToken(username, EXPIRATION_TIME );
//        response.addHeader(HEADER_STRING, token);
//        response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
//    }
//
//    @GetMapping("/validateToken")
//    public String validateToken(){
//        System.out.println("JwtController.validateToken");
//        return "필터를 무사히 통과하여 리턴됨";
//    }
//}
