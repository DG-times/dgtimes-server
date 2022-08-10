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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    
    // 뉴스 키워드 검색
    @GetMapping("/api/news")
    public ResponseEntity<SearchResponseDto[]> searchNews(@RequestParam String keyword) {
        System.out.println(keyword);
        return new ResponseEntity(searchService.getSeartchKeyword(new KeywordRequestDto(keyword)), HttpStatus.OK);
    }
}
