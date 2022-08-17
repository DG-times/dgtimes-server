//package com.v1.dgtimes.config.logging;
//
//import lombok.val;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.ILoggerFactory;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.logging.Logger;
//
//@Aspect
//@Component
//class LogAspect {

//    val log:Logger =LoggerFactory.getILoggerFactory(LogAspect::class.java)
//
//    @Before("bean(*Controller)")
//    fun beforeLog(joinPoint:JoinPoint) {
//        log.info("이름이 Controller로 끝나는 모든 @Bean 호출 전 실행되는 로그 입니다.")
//    }
//
//    @After("execution(* com.miot2j.service.*.*(..))
//            fun beforeLog(joinPoint:JoinPoint) {
//        log.info("service 패키지내의 모든 메소드 호출 후 실행되는 로그 입니다.")
//    }

//}