package com.v2.dgtimes.layer.realTimeSearchRanking.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "search_ranking_variation")
public class SearchRankingVariation {
    @Id
    private String value;
    private String isRankingUp;
}
