package com.v2.dgtimes.layer.news.controller;

import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import com.v2.dgtimes.layer.news.model.dto.response.NewsResponseDto;
import com.v2.dgtimes.layer.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;


    // 뉴스 키워드 검색 - 기존 작성했던 코드 방법
    @GetMapping("/api/news")
    public ResponseEntity<Page<NewsResponseDto>> searchNewsMatch(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(newsService.getSearchKeywordWithMatchAgainst(new NewsRequestDto(keyword), pageable), HttpStatus.OK);
    }

    @GetMapping("/api/newsLike")
    public ResponseEntity<Page<NewsResponseDto>> searchNewsLike(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(newsService.getSearchKeywordWithLike(new NewsRequestDto(keyword), pageable), HttpStatus.OK);
    }

    @GetMapping("/api/newsKeyword")
    public ResponseEntity<Page<NewsResponseDto>> searchNews(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(newsService.getSearchKeywordWithKeyword(new NewsRequestDto(keyword), pageable), HttpStatus.OK);
    }

}

