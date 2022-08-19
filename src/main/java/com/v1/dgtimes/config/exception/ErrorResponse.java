package com.v1.dgtimes.config.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
설명 : ErrorResponse 구현

작성일 : 2022.08.15

마지막 수정한 사람 : 안상록

*/
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private int status;

    public ErrorResponse(ErrorCode code){
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code){
        return new ErrorResponse(code);
    }
}
