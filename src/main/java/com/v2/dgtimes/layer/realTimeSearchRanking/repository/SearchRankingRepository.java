package com.v2.dgtimes.layer.realTimeSearchRanking.repository;

import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : 실시간 검색 랭킹 Repositoiry 입니다.

작성일 : 2022.09.03

마지막 수정한 사람 : 안상록

*/

public interface SearchRankingRepository extends JpaRepository<RealtimeSearchRanking, Long> {

}

