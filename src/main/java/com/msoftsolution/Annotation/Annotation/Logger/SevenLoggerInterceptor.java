package com.cfg.sevenapp.ms_intern.annotation;

import org.aspectj.lang.annotation.After;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StopWatch;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Aspect
public class SevenLoggerInterceptor {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    @Before(value = "com.cfg.sevenapp.ms_intern.annotation.SevenLog.auditLog()"
            + "&& target(bean) "
            + "&& @annotation(com.cfg.sevenapp.ms_intern.annotation.SevenLogger)"
            + "&& @annotation(logme)", argNames = "jp,bean,logme")
    public void log(JoinPoint jp, Object bean, SevenLogger logme) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS Z");
        String date = ZonedDateTime.now().format(formatter);
        System.out.println(date);        System.out.println(String.format("------------------start %s-----------------",jp.getSignature().getName()));
        System.out.println(date);
        if(auth.isAuthenticated()){
            System.out.println(ANSI_YELLOW + "username : "+auth.getName() +ANSI_RESET);
        }
        System.out.println(String.format("Log Message: %s", logme.message()));
        System.out.println(String.format("Bean Called: %s", bean.getClass().getName()));
        System.out.println(String.format("Method Called: %s", jp.getSignature().getName()));
    }

    @After(value = "com.cfg.sevenapp.ms_intern.annotation.SevenLog.auditLog()"
            + "&& target(bean) "
            + "&& @annotation(com.cfg.sevenapp.ms_intern.annotation.SevenLogger)"
            + "&& @annotation(logme)", argNames = "bean,logme")
    public void logAfter(JoinPoint jp, Object bean, SevenLogger logme) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS Z");
        String date = ZonedDateTime.now().format(formatter);
        System.out.println(date);
        System.out.println(String.format("------------------End %s-----------------",jp.getSignature().getName()));
    }

//    @Around(value = "com.cfg.sevenapp.ms_intern.annotation.SevenLog.auditLog()"
//            + "&& target(bean) "
//            + "&& @annotation(com.cfg.sevenapp.ms_intern.annotation.SevenLogger)"
//            + "&& @annotation(logme)", argNames = "joinPoint,bean,logme")
//    public void performanceLog(ProceedingJoinPoint joinPoint, Object bean, SevenLogger logme) throws Throwable {
//      System.out.println(String.format("------------------End %s-----------------",joinPoint.getSignature().getName()));
//
//
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        joinPoint.proceed();
//
//
//        stopWatch.stop();
//
//        StringBuffer logMessage = new StringBuffer();
//        logMessage.append(joinPoint.getTarget().getClass().getName());
//        logMessage.append(".");
//        logMessage.append(joinPoint.getSignature().getName());
//        logMessage.append("(");
//        // append args
//        Object[] args = joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            logMessage.append(args[i]).append(",");
//        }
//        if (args.length > 0) {
//            logMessage.deleteCharAt(logMessage.length() - 1);
//        }
//        logMessage.append(")");
//        logMessage.append(" execution time: ");
//        logMessage.append(stopWatch.getTotalTimeMillis());
//        logMessage.append(" ms");
//        System.out.println(logMessage.toString());
//    }


}
