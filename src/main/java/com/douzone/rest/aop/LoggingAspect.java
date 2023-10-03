package com.douzone.rest.aop;

import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.log.LogService;
import com.douzone.rest.log.vo.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    LogService logService;
    @Autowired
    public LoggingAspect(LogService logService) {
        this.logService = logService;
    }

    private ThreadLocal<String> userIdThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> ipAddressThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.douzone.rest.auth.AuthController.login(..)) && args(user) && args(request)")
    public void logBeforeLogin(UserVo user, HttpServletRequest request) {

        userIdThreadLocal.set(user.getUserId());
        ipAddressThreadLocal.set(request.getRemoteAddr());
        logger.info("User login request: {}", user.getUserId());
    }

    @AfterReturning(
            pointcut = "execution(* com.douzone.rest.auth.AuthController.login(..))",
            returning = "response"
    )
    public void logAfterLogin(JoinPoint joinPoint, ResponseEntity<ResponseVo> response) {

        String userId = userIdThreadLocal.get();
        String ipAddress = ipAddressThreadLocal.get();

        String loginResult = "SUCCESS";

        System.out.println("@@@@@@@@@@");
        System.out.println(ipAddress);
        System.out.println("@@@@@@@@@@");

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("User login succeeded. Response: {}", response.getBody());

            Log log = Log.builder()
                    .userId(userId)
                    .ipAddress(ipAddress)   // 임시
                    .result(loginResult)
                    .message(response.getBody().getMessage())
                    .requestUrl("/login")
                    .token(extractJwtTokenFromResponse(response))
                    .build();
            logService.insertLog(log);

        } else {
            logger.warn("User login failed. Response: {}", response.getBody());
            loginResult = "SUCCESS";
            Log log = Log.builder()
                    .companyCode(response.getBody().getUser().getCompanyCode())
                    .userId(userId)
                    .ipAddress(ipAddress)   // 임시
                    .result(loginResult)
                    .message(response.getBody().getMessage())
                    .requestUrl("/login")
                    .token(extractJwtTokenFromResponse(response))
                    .build();
            logService.insertLog(log);
        }



    }

    private String extractJwtTokenFromResponse(ResponseEntity<ResponseVo> response) {

        ResponseVo responseBody = response.getBody();
        if (responseBody != null && responseBody.getToken() != null ) {
            return responseBody.getToken();
        } else {
            return "Token not found";
        }
    }
}
