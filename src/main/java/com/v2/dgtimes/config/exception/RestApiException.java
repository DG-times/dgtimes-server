package com.v2.dgtimes.config.exception;

/*
설명 : RestApiException 구현

작성일 : 2022.08.16

마지막 수정한 사람 : 안상록

Todo -
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestApiException {
    private String msg;
    private String code;

    public RestApiException(ErrorCode errorCode){
        this.msg = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public static RestApiException of(ErrorCode errorCode){
        return new RestApiException(errorCode);
    }
}
