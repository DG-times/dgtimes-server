package com.v1.dgtimes.config.exception;

/*
설명 : CustomException 구현

작성일 : 2022.08.15

마지막 수정한 사람 : 안상록

*/

public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
