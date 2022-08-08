package com.v1.dgtimes.layer.model.exception;

/*
설명 : RestApiExceptionHandler 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleApiRequestException(RuntimeException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }
}