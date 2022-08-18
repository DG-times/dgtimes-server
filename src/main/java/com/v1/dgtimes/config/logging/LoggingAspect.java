package com.v1.dgtimes.config.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
class LoggingAspect {

    // ccom.v1.dgtimes.layer.controller 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.v1.dgtimes.layer.controller..*.*(..))")
    private void cut(){}

    //(방법2)Book Service 의 모든 메서드
    // @Around("execution(*com.example.demo.service.BookService.*(..))")
    // @Around("execution(*com.example.demo.controller..*.*(..))")
    // @Around("execution(*com.example.demo..*.*(..))")


    // Pointcut_에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg : args) {
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }
    }

    // Poincut_에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {

        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        log.info("return type = {}", returnObj.getClass().getSimpleName());
        log.info("return value = {}", returnObj);
    }

    // JoinPoint_로 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}