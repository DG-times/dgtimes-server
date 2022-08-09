package com.v1.dgtimes.integration;

/*
설명 : KeywordIntegrationTest 테스트 코드 구현
    > Class 이름 변경 - KeywordIntegrationTest -> SearchIntegrationTest
    > 모든 테스트 통과 완료

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.config.exception.RestApiException;
import com.v1.dgtimes.integration.mockobject.mockModel.MockKeywordModel;
import com.v1.dgtimes.integration.mockobject.mockRepository.MockKeywordRepository;
import com.v1.dgtimes.integration.mockobject.mockService.MockSearchService;
import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.KeywordMapping;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchIntegrationTest extends DefaultIntegrationTest{
    // 연관관계 매핑을 위한 참고 코드
//    private void createKeywordNewsData(String title, String content, String main_url, String thumbnail_url, String keyword) throws IOException {
//        Keyword keywordEntity = createKeywordData(keyword);
//        News newsEntity = createNewsData(title, content, main_url, thumbnail_url);
//        KeywordMapping keyword_mapping = new KeywordMapping();
//        keyword_mapping.updateKeywordNews(keywordEntity,newsEntity);
//    }
    // DB 접근 없이 진행 (mock 생성)
    @Test
    @DisplayName("검색 성공 케이스")
    public void case0() {
        // given
        String keyword = "코딩교육";
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto(keyword);

        MockKeywordRepository mockKeywordRepository = new MockKeywordRepository();
        MockSearchService mockSearchService = new MockSearchService(mockKeywordRepository);
        MockKeywordModel mockKeywordModel = mockSearchService.createKeyword(keywordRequestDto);
        //when
        Optional<MockKeywordModel> result = mockSearchService.searchKeyword(keywordRequestDto);
        //then
//        assertEquals(keyword,mockKeywordModel.getKeyword());
        assertEquals(keyword,result.get().getKeyword());
    }

    @Test
    @DisplayName("검색 성공 케이스")
    public void case1(){

        //given
        String keyword = "코딩교육";

        //when
        ResponseEntity<SearchResponseDto[]> response = testTemplate
                .getForEntity(
                        "/api/news?keyword="+keyword,
                        SearchResponseDto[].class
                );

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<SearchResponseDto> responseBody = Arrays.asList(response.getBody());
        assertNotNull(responseBody);

        SearchResponseDto searchResponseDto = responseBody.get(0);

        assertEquals(
                "코딩교육 팀스파르타, 상반기 매출 105억…’최대 실적 달성"
                ,searchResponseDto.getTitle());
        assertEquals(
                "코딩 교육 스타트업 팀스파르타가 올해 상반기 매출 105억원, 영업이익 31억원을 기록하며 최대 실적을 달성했다고 13일 밝혔다."
                ,searchResponseDto.getContent());
        assertEquals(
                "https://news.mt.co.kr/mtview.php?no=2022071316585964426"
                ,searchResponseDto.getMain_url());
        assertEquals(
                "https://thumb.mt.co.kr/06/2022/07/2022071316585964426_1.jpg/dims/optimize"
                ,searchResponseDto.getThumbnail());
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
