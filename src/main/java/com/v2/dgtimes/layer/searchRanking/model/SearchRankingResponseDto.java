package com.v2.dgtimes.layer.searchRanking.model;


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
public class SearchRankingResponseDto {
    @Id
    private String value;
    private String isRankingUp;

    @Override
    public String toString() {
        return "SearchRankingVariation{" +
                "value='" + value + '\'' +
                ", isRankingUp='" + isRankingUp + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String isRankingUp() {
        return isRankingUp;
    }

    public void setIsRankingUp(String isRankingUp) {
        this.isRankingUp = isRankingUp;
    }
}
