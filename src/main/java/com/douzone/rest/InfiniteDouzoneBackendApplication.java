package com.douzone.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy
@MapperScan("com.douzone.rest.**.dao")
public class InfiniteDouzoneBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiniteDouzoneBackendApplication.class, args);
    }

}
