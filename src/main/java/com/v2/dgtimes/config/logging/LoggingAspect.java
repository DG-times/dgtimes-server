package com.v2.dgtimes.config.logging;
import com.v2.dgtimes.layer.logging.model.SearchLog;
import com.v2.dgtimes.layer.logging.repository.SearchLogRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoggingAspect {

    private final SearchLogRepository searchLogRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("within(com.v2.dgtimes.layer.*.controller..*)")
    public void onRequest() {
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
            logger.info("[ Request ] : {} {} || QueryString : {} || {} || Time : ({}ms)",
                    request.getMethod(),
                    request.getRequestURL(),
                    request.getQueryString(),
                    paramMapToString(request.getParameterMap()), end - start);


            SearchLog searchLog = SearchLog.builder()
                    .keyword(Arrays.toString(request.getParameterMap().get("keyword")))
                    .includeKeywordList(Arrays.toString(request.getParameterMap().get("includeKeywordList")))
                    .excludeKeywordList(Arrays.toString(request.getParameterMap().get("excludeKeywordList")))
                    .build();

            if (request.getParameterMap().get("keyword") != null){
                searchLogRepository.save(searchLog);
            }

        }
    }


    private String paramMapToString(Map<String, String[]> paraStringMap) {
        return paraStringMap
                .entrySet()
                .stream()
                .map(entry -> String.format("%s : %s", entry.getKey(), Arrays.toString(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
}