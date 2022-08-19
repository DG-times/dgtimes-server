package com.v1.dgtimes.integration;

import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.config.exception.RestApiException;
import com.v1.dgtimes.config.security.PasswordEncoder;
import com.v1.dgtimes.layer.model.BlackKeyword;
import com.v1.dgtimes.layer.model.Bookmark;
import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
설명 : BookmarkIntegrationTest 테스트 코드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 김선진

*/


public class BookmarkIntegrationTest extends DefaultIntegrationTest{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setupDB(){
        userRepository.save(new User("admin",passwordEncoder.encode("testtest!!"),"kimseonjin"));
        keywordRepository.save(new Keyword("코딩교육"));
        blackKeywordRepository.save(new BlackKeyword("야한거"));
    }

    @AfterEach
    public void resetDB(){
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();
        keywordRepository.deleteAll();
        blackKeywordRepository.deleteAll();
    }



    // 키워드 저장 성공(통과)
    @Test
    @DisplayName("키워드 저장 성공")
    public void case1(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);

        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("키워드 저장에 성공했습니다.", response.getBody().getMsg());
        assertEquals(200, response.getBody().getStatus());
    }



    // 키워드 저장 실패 - 빈 키워드(통과)
    @Test
    @DisplayName("키워드 저장 실패 - 빈 키워드")
    public void case2(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);


        //when
        ResponseEntity<RestApiException> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        RestApiException.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_EMPTY_CODE.getMessage(), response.getBody().getMsg());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_EMPTY_CODE.getCode(), response.getBody().getCode());

    }



    @Test
    @DisplayName("키워드 저장 실패 - 로그인 되지 않음")
    public void case3(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);

        //when
        ResponseEntity<RestApiException> response = testTemplate
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        RestApiException.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.USER_LOGIN_NOT_CODE.getMessage(), response.getBody().getMsg());
        assertEquals(ErrorCode.USER_LOGIN_NOT_CODE.getCode(), response.getBody().getCode());
    }



    // 키워드 저장 실패 - 기존에 등록한 키워드
    @Test
    @DisplayName("키워드 저장 실패 - 기존에 등록한 키워드")
    public void case4(){
        //given
        User user = userRepository.findById("admin").get();
        Keyword keyword = keywordRepository.findByKeyword("코딩교육").get();
        Bookmark bookmark = new Bookmark(user, keyword);
        bookmarkRepository.save(bookmark);

        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);


        //when
        ResponseEntity<RestApiException> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        RestApiException.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_EXIST_USER_CODE.getMessage(), response.getBody().getMsg());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_EXIST_USER_CODE.getCode(), response.getBody().getCode());

    }



    // 키워드 저장 실패 - 금지된 키워드 (통과)
    @Test
    @DisplayName("키워드 저장 실패 - 금지된 키워드")
    public void case5(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("야한거");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);


        //when
        ResponseEntity<RestApiException> response = testTemplate
                .withBasicAuth("admin","testtest!!")
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        RestApiException.class
                );


        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_FORBIDDEN_CODE.getMessage(), response.getBody().getMsg());
        assertEquals(ErrorCode.BOOKMARK_KEYWORD_FORBIDDEN_CODE.getCode(), response.getBody().getCode());

    }

}
