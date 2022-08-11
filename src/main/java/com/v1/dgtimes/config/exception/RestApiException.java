package com.v1.dgtimes.config.exception;

/*
설명 : RestApiException 구현
    > 변수명 수정

작성일 : 2022.08.11

마지막 수정한 사람 : 홍우석

Todo -
*/

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    private String msg;
    private HttpStatus code;
}
