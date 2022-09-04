package com.v2.dgtimes.layer.news.service;

import com.v2.dgtimes.config.security.userdetail.UserDetailImpl;
import com.v2.dgtimes.layer.bookmark.service.BookmarkService;
import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import com.v2.dgtimes.layer.news.model.dto.response.NewsResponseDto;
import com.v2.dgtimes.layer.news.service.newsSerivceImple.GetNewsWithKeywordService;
import com.v2.dgtimes.layer.news.service.newsSerivceImple.GetNewsWithLikeService;
import com.v2.dgtimes.layer.news.service.newsSerivceImple.GetNewsWithMatchAgainstService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
설명 : NewsService
    - @Transaction(readOnly = true) 설정

작성일 : 2022.09.04

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class NewsService {
    private final GetNewsWithKeywordService getNewsWithKeywordService;
    private final GetNewsWithLikeService getNewsWithLikeService;
    private final GetNewsWithMatchAgainstService getNewsWithMatchAgainstService;
    private final BookmarkService bookmarkService;

    @Transactional(readOnly = true)
    public Page<NewsResponseDto> getSearchKeywordWithLike(NewsRequestDto newsRequestDto, Pageable pageable) {
        return getNewsWithLikeService.getSearchKeyword(newsRequestDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<NewsResponseDto> getSearchKeywordWithKeyword(NewsRequestDto newsRequestDto, Pageable pageable) {
        return getNewsWithKeywordService.getSearchKeyword(newsRequestDto, pageable);
    }

    public Page<NewsResponseDto> getSearchKeywordWithMatchAgainst(NewsRequestDto newsRequestDto, Pageable pageable, UserDetailImpl userDetail) {
        bookmarkService.saveBookmark(newsRequestDto, userDetail);

        return getNewsWithMatchAgainstService.getSearchKeyword(newsRequestDto, pageable);
    }
}
