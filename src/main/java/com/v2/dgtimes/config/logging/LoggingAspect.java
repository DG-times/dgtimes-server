package com.v2.dgtimes.config.logging;

import com.v1.dgtimes.config.exception.ExceptionHandlers;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/*
설명 : LoggingAspect 구현.

작성일 : 2022.09.02

담당자 : 공상욱

경로
@Pointcut("within(com.v2.dgtimes.layer.common.controller..*)")

 */

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("within(com.v2.dgtimes.layer.*.controller..*)")
    public void onRequest() {
    }

    @Pointcut("execution(* com.v2.dgtimes.config.exception..*(..))")
    public void ExceptionHandlers() {
    }


    @Around("com.v2.dgtimes.config.logging.LoggingAspect.onRequest()")
    public Object requestLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        long start = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            logger.info("\n Request : {} {}  \n QueryString : {} \n {}  \n Time : ({}ms)",
                    request.getMethod(),
                    request.getRequestURL(),
                    request.getQueryString(),
                    paramMapToString(request.getParameterMap()), end - start);
        }
    }


    @Around("ExceptionHandlers()")
    public ExceptionHandlers methodLoggingOnlyException(ProceedingJoinPoint com) throws Throwable {

        ExceptionHandlers exceptionHandlers = (ExceptionHandlers) com.proceed();
        Map<String, Object> params = new Hashtable<>();
        params.put("메시지", exceptionHandlers.toString());
        log.info("ExceptionHandlers : {}", params);
        return exceptionHandlers;
    }


    private String paramMapToString(Map<String, String[]> paraStringMap) {
        return paraStringMap
                .entrySet()
                .stream()
                .map(entry -> String.format("%s : %s", entry.getKey(), Arrays.toString(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
}