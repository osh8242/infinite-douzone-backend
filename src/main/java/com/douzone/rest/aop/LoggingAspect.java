package com.douzone.rest.aop;

import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.log.LogService;
import com.douzone.rest.log.vo.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    private String logDir;

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

        logger.info(user.toString());

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

        logService.insertLog(log);
    }

    private String extractJwtTokenFromResponse(ResponseEntity<ResponseVo> response) {

        ResponseVo responseBody = response.getBody();
        if (responseBody != null && responseBody.getToken() != null) {
            return responseBody.getToken();
        } else {
            return "Token not found";
        }
    }

    @Around("bean(*Controller)")
    public Object controllerAroundLogging(ProceedingJoinPoint pjp) throws Throwable {

        // 최근 요청
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        String requestUrl = request.getRequestURL().toString();

        // 현재 날짜
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String callFunction = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        Object result;
        try {
            result = pjp.proceed();
            String logMessage = "Controller Request Log - Date: " + formattedDate +
                    ", IP Address: " + ipAddress +
                    ", Request URL: " + requestUrl +
                    ", Controller Method: " + callFunction;

            log.info(logMessage);

        } catch (Exception e) {
            log.warn("Controller Request Log - Date: {}, IP Address: {}, Request URL: {}, Controller Method: {} - FAILED",
                    formattedDate, ipAddressThreadLocal.get(), requestUrlThreadLocal.get(), callFunction, e);
            throw e;
        }

        return result;
    }


    @Around("execution(* com.douzone.rest.*.service..*(..))")
    public Object serviceAroundLogging(ProceedingJoinPoint pjp) throws Throwable {

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String callFunction = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        Object result;
        try {
            result = pjp.proceed();
            String logMessage = "Service Method Log - Date: " + formattedDate +
                    ", Service Method: " + callFunction;
            log.info(logMessage);
        } catch (Exception e) {
            log.warn("Service Method Log - Date: " + formattedDate +
                    ", Service Method: " + callFunction + " - FAILED", e);
            throw e;
        }

        return result;
    }
}



