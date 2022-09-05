package com.v2.dgtimes.layer.realTimeSearchRanking.repository;

import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
설명 : 실시간 검색 랭킹 Repositoiry 입니다.
    - 랭킹 변동을 비교하기 위한 최신 데이터 2개를 가져오는 코드추가

작성일 : 2022.09.05

마지막 수정한 사람 : 안상록

*/

public interface SearchRankingRepository extends JpaRepository<RealtimeSearchRanking, Long> {
    List<RealtimeSearchRanking> findTop2ByOrderByDateDesc();
}

