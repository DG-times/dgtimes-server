package com.v1.dgtimes.integration;

import com.v1.dgtimes.config.exception.RestApiException;
import com.v1.dgtimes.layer.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
설명 : BookmarkIntegrationTest 테스트 코드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 김선진

*/



public class BookmarkIntegrationTest extends DefaultIntegrationTest{



//    @BeforeEach
//    public void setupDB(){
//        bookmarkRepository.save(new Keyword(new com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto("코당교육")));
//    }
//
//    @AfterEach
//    public void resetDB(){
//        bookmarkRepository.deleteAll();
//        userRepository.deleteAll();
//    }




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
        assertEquals("키워드 저장 실패 - 빈 키워드", response.getBody().getMsg());
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getCode());

    }




    @Test
    @DisplayName("키워드 저장 실패 - 로그인 되지 않음")
    public void case3(){
        //given
        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto("코딩교육");
        HttpEntity<BookmarkRequestDto> bookmarkRequestDtoHttpEntity = new HttpEntity<>(bookmarkRequestDto);


        //when
        ResponseEntity<DefaultResponseDto> response = testTemplate
                .postForEntity(
                        "/api/bookmarks",
                        bookmarkRequestDtoHttpEntity,
                        DefaultResponseDto.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("키워드 저장 실패 - 로그인 되지 않음", response.getBody().getMsg());
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().hashCode());
    }




    // 키워드 저장 실패 - 기존에 등록한 키워드
    @Test
    @DisplayName("키워드 저장 실패 - 기존에 등록한 키워드")
    public void case4(){
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
        assertEquals("키워드 저장 실패 - 기존에 등록한 키워드", response.getBody().getMsg());
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getCode());

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
        assertEquals("키워드 저장 실패 - 금지된 키워드", response.getBody().getMsg());
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getCode());

    }
}
