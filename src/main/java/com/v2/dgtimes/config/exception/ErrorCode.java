package com.v2.dgtimes.config.exception;

import lombok.Getter;

/*
설명 : ErrorCode 구현

작성일 : 2022.08.16

마지막 수정한 사람 : 안상록

*/
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //user
    ID_VALID_LENGTH_CODE(400, "U001", "회원가입에 실패했습니다. - 유효하지 않은 아이디 길이"),
    ID_VALID_PATTERN_MATCHES_CODE(400, "U002", "회원가입에 실패했습니다. - 유효하지 않은 아이디 형식"),
    PASSWORD_VALID_LENGTH_CODE(400, "U003", "회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이"),
    PASSWORD_CONTAIN_ID_CODE(400, "U004", "회원가입에 실패했습니다. - 비밀번호에 아이디 포함"),
    ID_EXIST_USER_CODE(400, "U005", "회원가입에 실패했습니다. - 중복된 아이디 입니다"),
    USER_LOGIN_NOT_CODE(400,"U006","로그인이 필요합니다."),

    //bookmark,
    BOOKMARK_KEYWORD_EMPTY_CODE(400,"B001","키워드 저장에 실패했습니다. - 빈 키워드입니다."),
    BOOKMARK_KEYWORD_EXIST_USER_CODE(400,"B002","키워드 저장에 실패했습니다. - 기존에 등록된 키워드입니다."),
    BOOKMARK_KEYWORD_FORBIDDEN_CODE(400,"B003","키워드 저장에 실패했습니다. - 금지된 키워드입니다."),
    BOOKMARK_KEYWORD_NOT_FOUND(400,"B004","키워드 저장에 실패했습니다. - 조회된 키워드가 없습니다."),


    //search
    SEARCH_KEYWORD_EMPTY_CODE(400,"S001","키워드 검색에 실패했습니다. - 키워드를 입력해주세요."),
    SEARCH_KEYWORD_FORBIDDEN_CODE(400,"S002","키워드 검색에 실패했습니다. - 금지된 키워드 입니다."),
    SEARCH_NEWS_NOT_FOUND_CODE(404,"S003","뉴스 검색에 실패했습니다. - 검색된 뉴스가 없습니다."),
    SEARCH_KEYWORD_NOT_FOUND_CODE(404,"S004","키워드 검색에 실패했습니다. - 찾는 키워드의 검색 결과가 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
