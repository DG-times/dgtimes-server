package com.v2.dgtimes.layer.searchRanking.controller;


import com.v2.dgtimes.layer.searchRanking.model.SearchRanking;
import com.v2.dgtimes.layer.searchRanking.model.SearchRankingResponseDto;
import com.v2.dgtimes.layer.searchRanking.repository.SearchRankingRepository;
import com.v2.dgtimes.layer.searchRanking.service.SearchRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
설명 : 실시간 검색 랭킹 controller 입니다.
    - 랭킹 Redis 적용 및 랭킹 데이터 저장 PostMapping 으로 수정

작성일 : 2022.09.07

마지막 수정한 사람 : 안상록

*/
@RestController
@RequiredArgsConstructor
public class SearchRankingController {

    private final SearchRankingService searchRankingService;
    private final SearchRankingRepository searchRankingRepository;


    @GetMapping("/api/RealTimeSearchRanking")
    public ResponseEntity getRealTimeSearchRankingVariation(){
        List<SearchRankingResponseDto> variation = searchRankingService.SearchRankingVariation();
        return new ResponseEntity(variation, HttpStatus.OK );
    }

}
