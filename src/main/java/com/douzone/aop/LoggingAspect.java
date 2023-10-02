package com.douzone.aop;

import com.douzone.rest.auth.vo.UserVo;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.douzone.rest.auth.AuthController.login(..)) && args(user)")
    public void logBeforeLogin(UserVo user) {
//        logger.info("User '{}'  log in", user.getUserId(), user.getUserName(),);
    }


}
