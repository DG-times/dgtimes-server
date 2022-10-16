package com.v2.dgtimes.layer.relation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RelationDto {
    private String keyword;
    private Double value;

    public RelationDto(LinkedHashMap linkedHashMap) {
        this.keyword = (String) linkedHashMap.get("related_keyword");
        this.value = (Double) linkedHashMap.get("similarity");
    }
}

