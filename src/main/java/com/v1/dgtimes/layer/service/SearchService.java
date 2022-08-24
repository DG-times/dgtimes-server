package com.v1.dgtimes.layer.service;

/*
설명 : SearchService 구현
    > 키워드 검색을 통한 뉴스 구현 완료
    > 코드 컨벤션 일부 적용 - 22.08.10
    > keyword and like search 로직 분리

작성일 : 2022.08.09

마지막 수정한 사람 : 김선진

Todo - Match Against Query 로직 추가하기!!
*/

import com.v1.dgtimes.config.exception.CustomException;
import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public abstract class SearchService {

    protected final KeywordRepository keywordRepository;
    protected final NewsRepository newsRepository;

    public abstract Page<SearchResponseDto> getSearchKeyword(KeywordRequestDto keywordRequestDto, Pageable pageable);

    protected Page<SearchResponseDto> makeSearchResponseDto(Page<News> news) {
        return news.map(n -> new SearchResponseDto(n));
    }

    // Keyword 검증 메소드 - 키워드 검색
    protected void validKeyword(KeywordRequestDto keywordRequestDto) {
        if (keywordRequestDto.isNone())
            throw new CustomException(ErrorCode.SEARCH_KEYWORD_EMPTY_CODE);
    }
}
