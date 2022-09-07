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

작성일 : 2022.09.05

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
    @Cacheable(value = "List<SearchRankingVariation>", key = "1", cacheManager = "cacheManager")
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

        return variationList;
    }

    @Transactional
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public SearchRanking SearchRanking(){

        HashMap<String, Integer> check = new HashMap<>();
        List<SearchLog> searchLogList = searchLogRepository.findAllById();

        for (SearchLog log : searchLogList){
            check.put(log.getKeyword(), check.getOrDefault(log.getKeyword(), 1) +1);
        }

        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(check.entrySet());

        entryList.sort((((o1, o2) -> check.get(o2.getKey())- check.get(o1.getKey()))));

        List<String> keywordList = new ArrayList<>();
        LocalDateTime date = searchLogList.get(searchLogList.size()-1).getTimestamp();
        for (Map.Entry<String, Integer> entry : entryList){
            keywordList.add(entry.getKey());
        }
        System.out.println("키워드 리스트 = "+keywordList);

        SearchRanking ranking = new SearchRanking(date, keywordList);
        repository.save(ranking);

        return ranking;
    }
}
