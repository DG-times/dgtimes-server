package com.v1.dgtimes.layer.controller;
/*
설명 : Search Controller 구현
    > 22.08.08 - searchNews 메소드 구현

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.service.SearchService;
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

    private final SearchService searchService;
    
    // 뉴스 키워드 검색 - 기존 작성했던 코드 방법
    @GetMapping("/api/news")
    public ResponseEntity<Page<SearchResponseDto>> searchNews(@RequestParam String keyword, Pageable pageable) {
        return new ResponseEntity(searchService.getSearchKeyword(new KeywordRequestDto(keyword), pageable), HttpStatus.OK);
    }

}
