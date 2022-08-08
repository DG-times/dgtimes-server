package com.v1.dgtimes.integration;

/*
설명 : KeywordIntegrationTest 테스트 코드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.layer.model.exception.RestApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


public class KeywordIntegrationTest extends DefaultIntegrationTest{

    @Test
    @DisplayName("검색 성공 케이스")
    public void case1(){
        //given
        String keyword = "코딩교육";

        //when
        ResponseEntity<SearchResponseDto> response = testTemplate
                .getForEntity(
                        "/api/news?keyword="+keyword,
                        SearchResponseDto.class
                );
        
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SearchResponseDto responseBody = response.getBody();
        assertNotNull(responseBody);

        assertEquals(
                "코딩교육 팀스파르타, 상반기 매출 105억…’최대 실적 달성"
                ,responseBody.getTitle());
        assertEquals(
                "코딩 교육 스타트업 팀스파르타가 올해 상반기 매출 105억원, 영업이익 31억원을 기록하며 최대 실적을 달성했다고 13일 밝혔다."
                ,responseBody.getContent());
        assertEquals(
                "https://news.mt.co.kr/mtview.php?no=2022071316585964426"
                ,responseBody.getMain_url());
        assertEquals(
                "https://thumb.mt.co.kr/06/2022/07/2022071316585964426_1.jpg/dims/optimize"
                ,responseBody.getThumbnail());
    }

    // Keyword 테이블에서 해당 키워드가 없는 경우 실패 반환
    @Test
    @DisplayName("찾는 키워드의 뉴스가 없어서 실패하는 케이스")
    public void case2(){
        //given
        String keyword = "coding Study";
        //when
        ResponseEntity<RestApiException> response = testTemplate
                .getForEntity(
                        "/api/news?keyword="+keyword,
                        RestApiException.class
                );
        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("찾는 키워드의 검색 결과가 없습니다.", responsebody.getErrorMessage());
    }

    // Service쪽에서 keyword의 값이 ""인 경우 실패 반환
    @Test
    @DisplayName("찾는 키워드가 공란(``)이여서 실패하는 케이스")
    public void case3(){
        //given
        String keyword = "";
        //when
        ResponseEntity<RestApiException> response = testTemplate
                .getForEntity(
                        "/api/news?keyword="+keyword,
                        RestApiException.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("키워드를 입력해주세요.", responsebody.getErrorMessage());
    }
    // Bookmark 테이블에서 금지어인지 확인 후, 금지어인경우 실패
    @Test
    @DisplayName("검색한 키워드가 금지어여서 실패하는 케이스")
    public void case4(){
        //given
        String keyword = "야한단어";
        //when
        ResponseEntity<RestApiException> response = testTemplate
                .getForEntity(
                        "/api/news?keyword="+keyword,
                        RestApiException.class
                );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("검색한 키워드 금지어입니다.", responsebody.getErrorMessage());
    }

}
