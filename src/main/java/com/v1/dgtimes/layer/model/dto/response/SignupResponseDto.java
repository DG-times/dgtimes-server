package com.v1.dgtimes.layer.model.dto.response;

import lombok.Builder;
import lombok.Getter;

/*
설명 : 유저의 회원가입이 정상적으로 값이 들어갔는지 확인을 위한 응답 데이터입니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 안상록

Todo -
*/
@Getter
@Builder
public class SignupResponseDto {
    private String id;
    private String pw;
    private String name;
}
