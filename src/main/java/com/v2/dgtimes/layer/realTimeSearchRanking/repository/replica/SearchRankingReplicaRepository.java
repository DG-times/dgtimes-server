package com.v2.dgtimes.layer.realTimeSearchRanking.repository.replica;

import com.v2.dgtimes.layer.realTimeSearchRanking.model.RealtimeSearchRanking;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : 실시간 검색 랭킹 읽기 전용 Repositoiry입니다

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
public interface SearchRankingReplicaRepository extends JpaRepository<RealtimeSearchRanking, Long> {
}
