package com.cfg.sevenapp.ms_intern.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SevenLog {


    @Pointcut("execution(* com.cfg.sevenapp.ms_intern.service.ServiceImpl.*.*(..))")
    public void auditLog() {}

    @Pointcut("execution(* com.cfg.sevenapp.ms_intern.service.ServiceImpl.*.*(..))")
    public void performanceLog(){

    }
}
