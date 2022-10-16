package com.v2.dgtimes.layer.searchRanking.repository;

import com.v2.dgtimes.layer.searchRanking.model.SearchRanking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
설명 : 실시간 검색 랭킹 Repository 입니다.
    - 랭킹 Redis 적용

작성일 : 2022.09.07

마지막 수정한 사람 : 안상록

*/

public interface SearchRankingRepository extends JpaRepository<SearchRanking, Long> {
    List<SearchRanking> findTop2ByOrderByDateDesc();
    SearchRanking findTopByOrderByDateDesc();

}

