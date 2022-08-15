package com.v1.dgtimes.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
설명 : ExceptionHandlers 구현

작성일 : 2022.08.15

마지막 수정한 사람 : 안상록

*/
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
