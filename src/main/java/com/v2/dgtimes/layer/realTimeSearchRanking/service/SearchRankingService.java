package com.v2.dgtimes.layer.realTimeSearchRanking.service;

import com.v2.dgtimes.layer.logging.model.SearchLog;
import com.v2.dgtimes.layer.logging.model.Timer;
import com.v2.dgtimes.layer.logging.repository.SearchLogRepository;
import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import com.v2.dgtimes.layer.realTimeSearchRanking.model.SearchRankingVariation;
import com.v2.dgtimes.layer.realTimeSearchRanking.repository.SearchRankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/*
설명 : 실시간 검색 랭킹 service 입니다.

작성일 : 2022.09.05

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class SearchRankingService {

    private final SearchRankingRepository repository;
    private final SearchLogRepository searchLogRepository;

    @Transactional
    @Cacheable(value = "List<SearchRankingVariation>", key = "1", cacheManager = "cacheManager")
    @Timer
    public List<SearchRankingVariation> SearchRankingVariation(){

        SearchRankingVariation variation = new SearchRankingVariation(); // 변동 사항
        List<SearchRankingVariation> variationList = new ArrayList<>();
        List<RealtimeSearchRanking> rankingList = repository.findTop2ByOrderByDateDesc();
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
            variation = SearchRankingVariation.builder()
                    .value(value)
                    .isRankingUp(isRankingUp)
                    .build();
            variationList.add(variation);
        }

        return variationList;
    }

    @Transactional
    public RealtimeSearchRanking SearchRanking(){

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

        RealtimeSearchRanking ranking = new RealtimeSearchRanking(date, keywordList);
        repository.save(ranking);

        return ranking;
    }
}
