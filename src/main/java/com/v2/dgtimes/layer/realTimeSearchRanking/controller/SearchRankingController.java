package com.v2.dgtimes.layer.realTimeSearchRanking.controller;

import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import com.v2.dgtimes.layer.realTimeSearchRanking.model.SearchRankingVariation;
import com.v2.dgtimes.layer.realTimeSearchRanking.repository.SearchRankingRepository;
import com.v2.dgtimes.layer.realTimeSearchRanking.service.SearchRankingService;
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
        List<SearchRankingVariation> variation = searchRankingService.SearchRankingVariation();
        return new ResponseEntity(variation, HttpStatus.OK );
    }

    @PostMapping("/api/ranking")
    public ResponseEntity getRealTimeSearchRanking(){
        RealtimeSearchRanking rankingList = searchRankingService.SearchRanking();
        return new ResponseEntity(rankingList, HttpStatus.OK );
    }

    @GetMapping("/api/ranking/redis")
    public ResponseEntity get(){
        List<RealtimeSearchRanking> rankingList = (List<RealtimeSearchRanking>) searchRankingRepository.findAll();
        return new ResponseEntity(rankingList, HttpStatus.OK );
    }
}
