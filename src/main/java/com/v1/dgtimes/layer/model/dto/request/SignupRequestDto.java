package com.v1.dgtimes.layer.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
설명 : 유저의 회원가입을 위한 요청된 정보입니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 안상록

Todo -
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String id;
    private String pw;
    private String username;
}
