package com.douzone.rest.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 테스트용
@RestController
@RequestMapping("/jwt")
public class JwtController {
    private JwtService jwtService;

    private final int expirationTime = 1000 * 60 * 10;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/getToken")
    public String getToken(@RequestParam("username") String username){
        System.out.println("JwtController.getToken");
        String token = jwtService.createToken("username", expirationTime);
        return token;
    }

    @GetMapping("/validateToken")
    public String validateToken(){
        System.out.println("JwtController.validateToken");
        return "필터를 무사히 통과하여 리턴됨";
    }
}
