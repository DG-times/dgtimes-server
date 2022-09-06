package com.v2.dgtimes.layer.realTimeSearchRanking.controller;

import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import com.v2.dgtimes.layer.realTimeSearchRanking.model.SearchRankingVariation;
import com.v2.dgtimes.layer.realTimeSearchRanking.service.SearchRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
설명 : 실시간 검색 랭킹 controller 입니다.
    - 랭캥 변동 확인을 위한 임시 controller

작성일 : 2022.09.05

마지막 수정한 사람 : 안상록

*/
@RestController
@RequiredArgsConstructor
public class SearchRankingController {

    private final SearchRankingService searchRankingService;

    @GetMapping("/api/RealTimeSearchRanking")
    public ResponseEntity getRealTimeSearchRankingVariation(){
        List<SearchRankingVariation> variation = searchRankingService.SearchRankingVariation();
        return new ResponseEntity(variation, HttpStatus.OK );
    }

    @GetMapping("/api/ranking")
    public ResponseEntity getRealTimeSearchRanking(){
        RealtimeSearchRanking rankingList = searchRankingService.SearchRanking();
        return new ResponseEntity(rankingList, HttpStatus.OK );
    }
}
