package com.v1.dgtimes.layer.service.SearchServiceImples;

import com.v1.dgtimes.config.exception.CustomException;
import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.config.logging.Timer;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
설명 : GetNewsWithKeywordService 구현
    > SearchService 에서 키워드 검색 로직을 분리

작성일 : 2022.08.24

마지막 수정한 사람 : 김선진

Todo -
*/

@Service
@RequiredArgsConstructor
public class GetNewsWithKeywordService{

    private final NewsRepository newsRepository;

    public Page<SearchResponseDto> getSearchKeyword(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        SearchServiceValidator.validKeyword(keywordRequestDto);
        Page<News> news = searchKeyword(keywordRequestDto, pageable);
        return SearchServiceValidator.makeSearchResponseDto(news);
    }

    @Timer
    private Page<News> searchKeyword(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        Page<News> news = newsRepository.findAllByKeyword(keywordRequestDto.getKeyword(), pageable);
        if (news.getTotalElements() == 0) {
            throw new CustomException(ErrorCode.SEARCH_NEWS_NOT_FOUND_CODE);
        }
        return news;
    }

}
