package com.v1.dgtimes.layer.service.SearchServiceImples;

import com.v1.dgtimes.config.exception.CustomException;
import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import com.v1.dgtimes.layer.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/*
설명 : GetNewsWithKeywordService 구현
    > SearchService 에서 LIKE 검색 로직을 분리

작성일 : 2022.08.24

마지막 수정한 사람 : 김선진

Todo -
*/

@Component
public class GetNewsWithLikeService extends SearchService {

    public GetNewsWithLikeService(KeywordRepository keywordRepository, NewsRepository newsRepository) {
        super(keywordRepository, newsRepository);
    }

    public Page<SearchResponseDto> getSearchKeyword(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        validKeyword(keywordRequestDto);
        Page<News> news = searchNews(keywordRequestDto, pageable);
        return makeSearchResponseDto(news);
    }

    // 뉴스 엔티티에서 keyword 검색
    private Page<News> searchNews(KeywordRequestDto keywordRequestDto, Pageable pageable) {
        Page<News> news = newsRepository.findAllByTitleAndContent(keywordRequestDto.getKeyword(), pageable);
        if (news.getTotalElements() == 0) {
            throw new CustomException(ErrorCode.SEARCH_NEWS_NOT_FOUND_CODE);
        }
        return news;
    }

}
