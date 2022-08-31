package com.v2.dgtimes.layer.news.service.newsSerivceImple;

import com.v2.dgtimes.config.exception.CustomException;
import com.v2.dgtimes.config.exception.ErrorCode;
import com.v2.dgtimes.layer.news.model.News;
import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import com.v2.dgtimes.layer.news.model.dto.response.NewsResponseDto;
import com.v2.dgtimes.layer.news.repository.NewsRepository;
import com.v2.dgtimes.layer.news.service.NewsServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/*
설명 : GetNewsWithKeywordService 구현
    > SearchService 에서 키워드 검색 로직을 분리

작성일 : 2022.08.24

마지막 수정한 사람 : 김선진

Todo -
*/

@Component
@RequiredArgsConstructor
public class GetNewsWithKeywordService{

    private final NewsRepository newsRepository;

    public Page<NewsResponseDto> getSearchKeyword(NewsRequestDto newsRequestDto, Pageable pageable) {
        NewsServiceValidator.validKeyword(newsRequestDto);
        Page<News> news = searchKeyword(newsRequestDto, pageable);
        return NewsServiceValidator.makeSearchResponseDto(news);
    }

    private Page<News> searchKeyword(NewsRequestDto newsRequestDto, Pageable pageable) {
        Page<News> news = newsRepository.findAllByKeyword(newsRequestDto.getKeyword(), pageable);
        if (news.getTotalElements() == 0) {
            throw new CustomException(ErrorCode.SEARCH_NEWS_NOT_FOUND_CODE);
        }
        return news;
    }

}
