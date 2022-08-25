package com.v1.dgtimes.layer.service.SearchServiceImples;

import com.v1.dgtimes.config.exception.CustomException;
import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import org.springframework.data.domain.Page;


public class SearchServiceValidator {
    public static Page<SearchResponseDto> makeSearchResponseDto(Page<News> news) {
        return news.map(n -> new SearchResponseDto(n));
    }

    // Keyword 검증 메소드 - 키워드 검색
    public static void validKeyword(KeywordRequestDto keywordRequestDto) {
        if (keywordRequestDto.isNone())
            throw new CustomException(ErrorCode.SEARCH_KEYWORD_EMPTY_CODE);
    }
}
