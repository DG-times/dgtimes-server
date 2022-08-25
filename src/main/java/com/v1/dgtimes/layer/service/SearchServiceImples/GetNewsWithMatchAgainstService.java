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

@Service
@RequiredArgsConstructor
public class GetNewsWithMatchAgainstService {

    private final NewsRepository newsRepository;

    public Page<SearchResponseDto> getSearchKeyword(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        SearchServiceValidator.validKeyword(keywordRequestDto);
        Page<News> news = searchNews(keywordRequestDto, pageable);
        return SearchServiceValidator.makeSearchResponseDto(news);
    }

    // 뉴스 엔티티에서 keyword 검색
    @Timer
    private Page<News> searchNews(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        Page<News> news = newsRepository.findAllByMatch(keywordRequestDto.getKeyword(), pageable);
        if (news.getTotalElements() == 0) {
            throw new CustomException(ErrorCode.SEARCH_NEWS_NOT_FOUND_CODE);
        }
        return news;
    }
}
