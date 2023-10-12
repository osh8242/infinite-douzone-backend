//package com.douzone.rest.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import org.aspectj.lang.annotation.Around;
//
//@Aspect
//@Component
public class PerformanceAspect {
//    private int daoMethodCallCount = 0;
//
//    @After("execution(* com.douzone.rest.saallowpay.dao.*.*(..))")
//    public void countDaoMethodCalls() {
//        daoMethodCallCount++;
//    }
//
//    public int getDaoMethodCallCount() {
//        return daoMethodCallCount;
//    }
//
//    @Around("execution(* com.douzone.rest.saallowpay.controller.*.*(..))")
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
//            System.out.println("=================================================================================");
//            System.out.println(joinPoint.getSignature() + " 실행 시간: " + executionTime + "ms");
//            System.out.println("=================================================================================");
//            return result;
//
//        } catch (Throwable e) {
//            throw e;
//        }
//    }
}

