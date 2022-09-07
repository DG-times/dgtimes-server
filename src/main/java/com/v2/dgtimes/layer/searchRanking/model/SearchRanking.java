package com.v2.dgtimes.layer.searchRanking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "realtime_search_ranking")
public class SearchRanking {
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
    public SearchRanking(LocalDateTime date, List<String> keywordList) {
        this.date = date;
        this.setKeywordList(keywordList);
    }
}
