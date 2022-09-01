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

@Component
@RequiredArgsConstructor
public class GetNewsWithMatchAgainstService {

    private final NewsRepository newsRepository;

    public Page<NewsResponseDto> getSearchKeyword(NewsRequestDto newsRequestDto, Pageable pageable) {
        NewsServiceValidator.validKeyword(newsRequestDto);
        Page<News> news = searchNews(newsRequestDto, pageable);
        return NewsServiceValidator.makeSearchResponseDto(news);
    }

    // 뉴스 엔티티에서 keyword 검색
    private Page<News> searchNews(NewsRequestDto newsRequestDto, Pageable pageable) {
        Page<News> news = newsRepository.findAllByMatch(newsRequestDto.getKeyword(), pageable);
        if (news.getTotalElements() == 0) {
            throw new CustomException(ErrorCode.SEARCH_NEWS_NOT_FOUND_CODE);
        }
        return news;
    }
}
