package com.v2.dgtimes.layer.news.service;

import com.v2.dgtimes.config.exception.CustomException;
import com.v2.dgtimes.config.exception.ErrorCode;
import com.v2.dgtimes.layer.news.model.News;
import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import com.v2.dgtimes.layer.news.model.dto.response.NewsResponseDto;
import org.springframework.data.domain.Page;


public class NewsServiceValidator {
    public static Page<NewsResponseDto> makeSearchResponseDto(Page<News> news) {
        return news.map(n -> new NewsResponseDto(n));
    }

    // Keyword 검증 메소드 - 키워드 검색
    public static void validKeyword(NewsRequestDto newsRequestDto) {
        if (newsRequestDto.isNone())
            throw new CustomException(ErrorCode.SEARCH_KEYWORD_EMPTY_CODE);
    }
}
