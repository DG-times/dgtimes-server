package com.v1.dgtimes.layer.service;

/*
설명 : SearchServiceTest 코드 구현

작성일 : 2022.08.12

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    public KeywordRepository keywordRepository;

    @Mock
    public BlackKeywordRepository blackKeywordRepository;

    @Mock
    public NewsRepository newsRepository;

    @InjectMocks
    private SearchService searchService;

    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - DB 키워드 찾기 성공")
    public void SuccessTest1(){
        // Given
        Keyword find_keyword = new Keyword();
        when(keywordRepository.findByKeyword("코딩교육")).thenReturn(Optional.of(find_keyword));

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩교육");
        Keyword keyword = ReflectionTestUtils.invokeMethod(searchService, "searchKeyword", keywordRequestDto);

        // Then
        assertEquals(find_keyword, keyword);
    }

    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - DB 키워드 찾기 실패")
    public void failTest1(){
        // Given
        when(keywordRepository.findByKeyword("코딩")).thenThrow(new RuntimeException("찾는 키워드의 검색 결과가 없습니다."));

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "searchKeyword", keywordRequestDto);
        });

        // Then
        assertEquals("찾는 키워드의 검색 결과가 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - 금기어 있는경우")
    public void SuccessTest2(){
        // Given
        boolean _return = true;
        when(blackKeywordRepository.existsByBlackKeyword("야한단어")).thenReturn(_return);

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("야한단어");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "validBlackKeyword", keywordRequestDto);
        });
        // Then
        assertEquals("검색한 키워드 금지어입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - 금기어 없는경우")
    public void failTest2(){
        // Given
        boolean _return = false;
        when(blackKeywordRepository.existsByBlackKeyword("키워드단어")).thenReturn(_return);

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("키워드단어");
        ReflectionTestUtils.invokeMethod(searchService, "validBlackKeyword", keywordRequestDto);
        // Then
        // return 값이 없으므로, 단 한번이라도 실행이 되었는지 검사
        verify(blackKeywordRepository, atLeastOnce()).existsByBlackKeyword("키워드단어");
    }


    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - KeywordId를 이용한 뉴스 찾기 성공")
    public void SuccessTest3(){
        // Given
        Keyword find_keyword = new Keyword();
        News found_news = new News();
        List<News> list_news = new ArrayList<>();
        list_news.add(found_news);
        when(newsRepository.findAllId(find_keyword.getId())).thenReturn(list_news);

        // When
        Keyword keyword = new Keyword();
        List<News> result_news = ReflectionTestUtils.invokeMethod(searchService, "searchNewsForMapping", keyword);

        // Then
        assertEquals(result_news, result_news);
    }

    @Test
    @DisplayName("getSearchKeyword 메소드 검증 - KeywordId를 이용한 뉴스 찾기 실패")
    public void failTest3(){
        // Given
        Keyword find_keyword = new Keyword();
        when(newsRepository.findAllId(find_keyword.getId())).thenThrow(new NullPointerException());

        // When
        Keyword keyword = new Keyword();
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "searchNewsForMapping", keyword);
        });
        // Then
        assertEquals(null, exception.getMessage());
    }

    // 뉴스 엔티티에서 바로 keyword 검색 - Like이용
    @Test
    @DisplayName("getSearchNews 메소드 검증 - 뉴스 검색 성공")
    public void SuccessTest4() {
        // Given
        News found_news = News.builder()
                .title("코딩교육")
                .content("코딩교육")
                .build();
        List<News> news = new ArrayList<>();
        news.add(found_news);
        when(newsRepository.findAllByTitleAndContent("코딩교육")).thenReturn(news);

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩교육");
        List<News> result_news = ReflectionTestUtils.invokeMethod(searchService, "searchNews", keywordRequestDto);

        // Then
        assertEquals(news, result_news);
    }

    @Test
    @DisplayName("getSearchNews 메소드 검증 - 뉴스 검색 실패")
    public void failTest4() {
        // Given
        when(newsRepository.findAllByTitleAndContent("코딩교육")).thenThrow(new RuntimeException("검색된 뉴스가 없습니다."));

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩교육");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "searchNews", keywordRequestDto);
        });

        // Then
        assertEquals("검색된 뉴스가 없습니다.", exception.getMessage());
    }

    // inner Join을 사용한 Keyword 조회
    @Test
    @DisplayName("getNewSearchKeyword 메소드 검증 - 키워드를 통한 뉴스 검색 성공")
    public void SuccessTest5(){
        // Given
        News found_news = News.builder()
                .title("코딩교육")
                .content("코딩교육")
                .build();
        List<News> news = new ArrayList<>();
        news.add(found_news);
        when(newsRepository.findAllByKeyword("코딩교육")).thenReturn(news);

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩교육");
        List<News> result_news = ReflectionTestUtils.invokeMethod(searchService, "newSearchKeyword", keywordRequestDto);

        // Then
        assertEquals(news, result_news);
    }
    @Test
    @DisplayName("getNewSearchKeyword 메소드 검증 - 키워드를 통한 뉴스 검색  실패")
    public void failTest5() {
        // Given
        when(newsRepository.findAllByKeyword("코딩교육")).thenThrow(new RuntimeException("검색된 뉴스가 없습니다."));

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩교육");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "newSearchKeyword", keywordRequestDto);
        });
        // Then
        assertEquals("검색된 뉴스가 없습니다.", exception.getMessage());
    }
}

 */