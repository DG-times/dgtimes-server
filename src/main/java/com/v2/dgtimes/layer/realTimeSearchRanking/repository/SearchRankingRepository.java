package com.v2.dgtimes.layer.realTimeSearchRanking.repository;

import com.v2.dgtimes.layer.realTimeSearchRanking.repository.replica.SearchRankingReplicaRepository;
import com.v2.dgtimes.layer.realTimeSearchRanking.repository.source.SearchRankingSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
설명 : 실시간 검색 랭킹 Repositoiry 입니다.

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/

@Component
@RequiredArgsConstructor
public class SearchRankingRepository {
    private final SearchRankingReplicaRepository replicaRepository;
    private final SearchRankingSourceRepository sourceRepository;

}
