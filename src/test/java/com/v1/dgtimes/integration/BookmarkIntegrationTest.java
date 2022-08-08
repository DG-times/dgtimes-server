package com.v1.dgtimes.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
설명 : BookmarkIntegrationTest 테스트 코드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 김선진

*/

public class BookmarkIntegrationTest extends DefaultIntegrationTest{
    @Test
    @DisplayName("키워드 저장 성공")
    public void case1(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDto,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new DefaultResponseDto("회원가입에 성공했습니다.",200), response.getBody());
    }

    @Test
    @DisplayName("키워드 저장 실패 - 빈 키워드")
    public void case2(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("");

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDto,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("키워드를 입력해주세요.",400), response.getBody());
    }

    @Test
    @DisplayName("키워드 저장 실패 - 로그인 되지 않음")
    public void case3(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDto,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("로그인이 필요합니다.",400), response.getBody());
    }

    @Test
    @DisplayName("키워드 저장 실패 - 기존에 등록한 키워드")
    public void case4(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDto,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("기존에 등록한 키워드 입니다.",400), response.getBody());
    }

    @Test
    @DisplayName("키워드 저장 실패 - 금지된 키워드")
    public void case5(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("야한거");

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDto,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("금지된 키워드 입니다.",400), response.getBody());
    }
}
