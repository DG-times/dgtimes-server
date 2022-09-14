package com.v2.dgtimes.layer.searchRanking.service;

import com.v2.dgtimes.layer.logging.model.SearchLog;
import com.v2.dgtimes.layer.logging.model.Timer;
import com.v2.dgtimes.layer.logging.repository.SearchLogRepository;
import com.v2.dgtimes.layer.searchRanking.model.SearchRanking;
import com.v2.dgtimes.layer.searchRanking.model.SearchRankingResponseDto;
import com.v2.dgtimes.layer.searchRanking.repository.SearchRankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
설명 : 실시간 검색 랭킹 service 입니다.

작성일 : 2022.09.08

마지막 수정한 사람 : 안상록

TODO
- 1. SearchRanking 로직에서 로그가 0인 경우, 즉 검색 기록이 없는 경우 INDEX OUT RANGE 에러 핸들링하기
- 2. 이전 검색 기록과 비교해서 10개가 안되는 경우, 이전 검색 기록의 TOP을 가져와서 아래에 매핑하기!

*/
@Service
@RequiredArgsConstructor
public class SearchRankingService {

    private final SearchRankingRepository repository;
    private final SearchLogRepository searchLogRepository;

    @Transactional
    @Timer
    public List<SearchRankingResponseDto> SearchRankingVariation(){

        SearchRankingResponseDto variation = new SearchRankingResponseDto(); // 변동 사항
        List<SearchRankingResponseDto> variationList = new ArrayList<>();
        List<SearchRanking> rankingList = repository.findTop2ByOrderByDateDesc();
        List<String> presentRankList = rankingList.get(0).getKeywordList();
        List<String> pastRankList = rankingList.get(1).getKeywordList();

        for (int i = 0; i < presentRankList.size(); i++) {
            int presentRank = i+1;
            int pastRank;
            String value = presentRankList.get(i);
            String isRankingUp = null;

            if (pastRankList.contains(value)){
                pastRank = pastRankList.indexOf(value)+1;
                if (pastRank > presentRank){
                    isRankingUp = "+";
                }else if (pastRank < presentRank){
                    isRankingUp = "-";
                }
            }else if (!pastRankList.contains(value)){
                isRankingUp = "new";
            }
            variation = SearchRankingResponseDto.builder()
                    .value(value)
                    .isRankingUp(isRankingUp)
                    .build();
            variationList.add(variation);

        }

        if (variationList.size() < 10){
            String isRankingUp = "-";
            for (String value : pastRankList){
                if (!presentRankList.contains(value)){
                    variation = SearchRankingResponseDto.builder()
                            .value(value)
                            .isRankingUp(isRankingUp)
                            .build();
                    variationList.add(variation);
                }
                if (variationList.size() == 10){
                    break;
                }
            }
        }

        return variationList;
    }

}
