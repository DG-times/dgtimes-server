package com.v1.dgtimes.config.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/*
설명 : ErrorCode 구현

작성일 : 2022.08.15

마지막 수정한 사람 : 안상록

*/
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    ID_VALID_LENGTH_CODE(400, "U001", "회원가입에 실패했습니다. - 유효하지 않은 아이디 길이"),
    ID_VALID_PATTERN_MATCHES_CODE(400, "U002", "회원가입에 실패했습니다. - 유효하지 않은 아이디 형식"),
    PASSWORD_VALID_LENGTH_CODE(400, "U003", "회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이"),
    PASSWORD_CONTAIN_ID_CODE(400, "U004", "회원가입에 실패했습니다. - 비밀번호에 아이디 포함"),
    ID_EXIST_USER_CODE(400, "U005", "회원가입에 실패했습니다. - 중복된 아이디 입니다");
    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
