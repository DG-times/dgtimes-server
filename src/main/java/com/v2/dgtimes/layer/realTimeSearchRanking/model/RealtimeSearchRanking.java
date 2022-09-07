package com.v2.dgtimes.layer.realTimeSearchRanking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*
설명 : 실시간 검색 랭킹 모델 입니다.
    - 랭킹 Redis 적용

작성일 : 2022.09.07

마지막 수정한 사람 : 안상록

*/
@Entity
@Getter
@NoArgsConstructor
@Table(name = "realtime_search_ranking")
//@RedisHash(value = "RealtimeSearchRanking", timeToLive = 3600)
public class RealtimeSearchRanking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="TIMESTAMP")
    private LocalDateTime date;
    private String keywordList;

    public List<String> getKeywordList() {
        return Arrays.asList(keywordList.split(","));
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = String.join(",", keywordList);
    }

    @Builder
    public RealtimeSearchRanking(LocalDateTime date, List<String> keywordList) {
        this.date = date;
        this.setKeywordList(keywordList);
    }
}
