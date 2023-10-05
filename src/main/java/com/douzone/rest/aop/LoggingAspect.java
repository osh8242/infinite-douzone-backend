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
import java.util.Enumeration;
import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {
    LogService logService;
    @Autowired
    public LoggingAspect(LogService logService) {
        this.logService = logService;
    }

    private ThreadLocal<String> companyCodeThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> userIdThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> ipAddressThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> requestUrlThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> queryStringThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> requestMethodThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.douzone.rest.auth.AuthController.login(..)) && args(user) && args(request)")
    public void logBeforeLogin(UserVo user, HttpServletRequest request) {

        userIdThreadLocal.set(user.getUserId());
        String clientIpAddress = "";

        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            String[] ipAddresses = xForwardedForHeader.split(",");
            clientIpAddress = ipAddresses[0].trim();
        }

        ipAddressThreadLocal.set(clientIpAddress);
        requestUrlThreadLocal.set(request.getRequestURL().toString());
        queryStringThreadLocal.set(request.getQueryString());
        requestMethodThreadLocal.set(request.getMethod());
    }

    @AfterReturning(
            pointcut = "execution(* com.douzone.rest.auth.AuthController.login(..))",
            returning = "response"
    )
    public void logAfterLogin(JoinPoint joinPoint, ResponseEntity<ResponseVo> response) {

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("User login succeeded. Response: {}", response.getBody());

        } else {
            logger.warn("User login failed. Response: {}", response.getBody());
        }

        Log.LogBuilder logBuilder = Log.builder()
                .companyCode(Optional.ofNullable(response.getBody().getUser().getCompanyCode()).orElse(""))
                .userId(Optional.ofNullable(userIdThreadLocal.get()).orElse(""))
                .ipAddress(Optional.ofNullable(ipAddressThreadLocal.get()).orElse(""))
                .token(Optional.ofNullable(extractJwtTokenFromResponse(response)).orElse(""))
                .requestUrl(Optional.ofNullable(requestUrlThreadLocal.get()).orElse(""))
                .message(Optional.ofNullable(response.getBody().getMessage()).orElse(""))
                .queryString(Optional.ofNullable(queryStringThreadLocal.get()).orElse(""));

        Log log = logBuilder.build();

        //logService.insertLog(log);
    }

    private String extractJwtTokenFromResponse(ResponseEntity<ResponseVo>  response) {

        ResponseVo responseBody = response.getBody();
        if (responseBody != null && responseBody.getToken() != null ) {
            return responseBody.getToken();
        } else {
            return "Token not found";
        }
    }



}
