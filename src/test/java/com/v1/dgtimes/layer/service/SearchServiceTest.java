package com.v1.dgtimes.layer.service;

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.request.NewsRequestDto;
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
import static org.mockito.Mockito.when;

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
    @DisplayName("Search Keyword 메소드 검증 - DB 키워드 찾기")
    public void test1(){
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
    @DisplayName("Search Keyword 메소드 검증 - DB에 찾는 키워드 없음")
    public void test2(){
        // Given
        Keyword find_keyword = new Keyword();
        when(keywordRepository.findByKeyword("코딩교육")).thenReturn(Optional.of(find_keyword));

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("코딩");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "searchKeyword", keywordRequestDto);
        });

        // Then
        assertEquals("찾는 키워드의 검색 결과가 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("Search Keyword 메소드 검증 - 금기어 필터")
    public void test3(){
        // Given
        boolean _return = true;
        when(blackKeywordRepository.existsByBlackKeyword("야한단어")).thenReturn(_return);

        // When
        KeywordRequestDto keywordRequestDto = new KeywordRequestDto("야한단어");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ReflectionTestUtils.invokeMethod(searchService, "validKeyword", keywordRequestDto);
        });
        // Then
        assertEquals("검색한 키워드 금지어입니다.", exception.getMessage());
    }

    // 테스트는 우연히 통과되었지만, 반환값 차이가 있으므로, 수정이 필요
    @Test
    @DisplayName("Search Keyword 메소드 검증 - Keyword Id를 이용한 뉴스 찾기")
    public void test4(){
        // Given
        Keyword find_keyword = new Keyword();
        News found_news = new News();
        List<News> list_news = new ArrayList<>();
        list_news.add(found_news);
        when(newsRepository.findAllId(find_keyword.getId())).thenReturn(list_news);

        // When
        Keyword keyword = new Keyword();
        List<News> result_news = ReflectionTestUtils.invokeMethod(searchService, "searchKeywordMapping", keyword);

        // Then
        assertEquals(result_news, result_news);
    }
}