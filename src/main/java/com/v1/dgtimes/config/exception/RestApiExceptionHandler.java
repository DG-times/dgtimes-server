package com.v1.dgtimes.config.exception;

/*
설명 : RestApiExceptionHandler 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

//    @ExceptionHandler(value = {RuntimeException.class})
//    public ResponseEntity<Object> handleApiRequestException(RuntimeException ex) {
//        RestApiException restApiException = new RestApiException();
//        restApiException.setCode(HttpStatus.BAD_REQUEST);
//        restApiException.setMsg(ex.getMessage());
//
//        return new ResponseEntity(
//                restApiException,
//                HttpStatus.BAD_REQUEST
//        );
//    }
}