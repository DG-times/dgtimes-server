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

@Service
@RequiredArgsConstructor
public class NewsService {
    private final GetNewsWithKeywordService getNewsWithKeywordService;
    private final GetNewsWithLikeService getNewsWithLikeService;
    private final GetNewsWithMatchAgainstService getNewsWithMatchAgainstService;

    private final BookmarkService bookmarkService;

    public Page<NewsResponseDto> getSearchKeywordWithLike(NewsRequestDto newsRequestDto, Pageable pageable) {
        return getNewsWithLikeService.getSearchKeyword(newsRequestDto, pageable);
    }

    public Page<NewsResponseDto> getSearchKeywordWithKeyword(NewsRequestDto newsRequestDto, Pageable pageable) {
        return getNewsWithKeywordService.getSearchKeyword(newsRequestDto, pageable);
    }

    public Page<NewsResponseDto> getSearchKeywordWithMatchAgainst(NewsRequestDto newsRequestDto, Pageable pageable, UserDetailImpl userDetail) {
        bookmarkService.saveBookmark(newsRequestDto, userDetail);
        return getNewsWithMatchAgainstService.getSearchKeyword(newsRequestDto, pageable);
    }
}
