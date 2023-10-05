package com.douzone.rest.aop;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class PerformanceAspect {

//    @Around("execution(* com.douzone.rest.*.*.*(..))")
//    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long startTime = System.currentTimeMillis();
//
//        try {
//            // 원래 메서드 실행
//            Object result = joinPoint.proceed();
//
//            // 실행 시간 측정
//            long endTime = System.currentTimeMillis();
//            long executionTime = endTime - startTime;
//
//            // 실행 시간 로깅
//            System.out.println(joinPoint.getSignature() + " 실행 시간: " + executionTime + "ms");
//
//            return result;
//
//        } catch (Throwable e) {
//            // 예외 처리
//            throw e;
//        }
//    }
}

