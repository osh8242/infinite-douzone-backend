package com.douzone.rest.aop;

import com.douzone.rest.auth.vo.ResponseVo;
import com.douzone.rest.auth.vo.UserVo;
import com.douzone.rest.log.LogService;
import com.douzone.rest.log.vo.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Aspect
@Slf4j
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

        logService.insertLog(log);
    }

    private String extractJwtTokenFromResponse(ResponseEntity<ResponseVo>  response) {

        ResponseVo responseBody = response.getBody();
        if (responseBody != null && responseBody.getToken() != null ) {
            return responseBody.getToken();
        } else {
            return "Token not found";
        }
    }
//
//    @Pointcut("within(* com.douzone.rest.*..*.*(..))")
//    private void onRequest(){}
//    // POST
//
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
//
//// GET
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")

    /* Pointcut 과 매칭되는 메서드의 실행 전, 후에 실행
     *  @Around advice 는 꼭 proceed()가 필요하다. */
//    @Around("onRequest()")
//    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable{
//        Class clazz = joinPoint.getTarget().getClass();
//        Logger logger = LoggerFactory.getLogger(clazz);
//        Object result = null;
//        try {
//            result = joinPoint.proceed(joinPoint.getArgs());
//            return result;
//        } finally {
//            logger.info(getRequestUrl(joinPoint, clazz));
//            logger.info("parameters" + JSON.toJSONString(params(joinPoint)));
//            logger.info("response: " + JSON.toJSONString(result, true));
//        }
//    }
//

//    private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
//        String baseUrl = requestMapping.value()[0];
//
//        String url = Stream.of( GetMapping.class, PutMapping.class, PostMapping.class,
//                        PatchMapping.class, DeleteMapping.class, RequestMapping.class)
//                .filter(mappingClass -> method.isAnnotationPresent(mappingClass))
//                .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
//                .findFirst().orElse(null);
//        return url;
//    }
//
//    /* httpMETHOD + requestURI 를 반환 */
//    private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
//        Annotation annotation = method.getAnnotation(annotationClass);
//        String[] value;
//        String httpMethod = null;
//        try {
//            value = (String[])annotationClass.getMethod("value").invoke(annotation);
//            httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
//        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//            return null;
//        }
//        return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "") ;
//    }
//
//
//    private Map params(JoinPoint joinPoint) {
//        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
//        String[] parameterNames = codeSignature.getParameterNames();
//        Object[] args = joinPoint.getArgs();
//        Map<String, Object> params = new HashMap<>();
//        for (int i = 0; i < parameterNames.length; i++) {
//            params.put(parameterNames[i], args[i]);
//        }
//        return params;
//    }




}
