package com.v1.dgtimes.layer.controller;
/*
설명 : Search Controller 구현
    > 22.08.08 - searchNews 메소드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.config.logging.Timer;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.NewsRepository;
import com.v1.dgtimes.layer.service.SearchServiceImples.GetNewsWithKeywordService;
import com.v1.dgtimes.layer.service.SearchServiceImples.GetNewsWithLikeService;
import com.v1.dgtimes.layer.service.SearchServiceImples.GetNewsWithMatchAgainstService;
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
public class SearchController {

    private final GetNewsWithKeywordService getNewsWithKeywordService;
    private final GetNewsWithLikeService getNewsWithLikeService;
    private final GetNewsWithMatchAgainstService getNewsWithMatchAgainstService;


    // 뉴스 키워드 검색 - 기존 작성했던 코드 방법
    @GetMapping("/api/news")
    public ResponseEntity<Page<SearchResponseDto>> searchNewsMatch(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(getNewsWithMatchAgainstService.getSearchKeyword(new KeywordRequestDto(keyword), pageable), HttpStatus.OK);
    }

    @GetMapping("/api/newsLike")
    public ResponseEntity<Page<SearchResponseDto>> searchNewsLike(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(getNewsWithLikeService.getSearchKeyword(new KeywordRequestDto(keyword), pageable), HttpStatus.OK);
    }

    @GetMapping("/api/newsMatch")
    public ResponseEntity<Page<SearchResponseDto>> searchNews(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(getNewsWithKeywordService.getSearchKeyword(new KeywordRequestDto(keyword), pageable), HttpStatus.OK);
    }

}
