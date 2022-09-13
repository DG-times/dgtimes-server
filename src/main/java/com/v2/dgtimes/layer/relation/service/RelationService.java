package com.v2.dgtimes.layer.relation.service;

import com.v2.dgtimes.layer.relation.model.RelationDto;
import com.v2.dgtimes.layer.relation.repository.RelationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final RelationClient relationClient;

    public List<RelationDto> getMentionRelation(String keyword){
        return relationClient.getMentionRelation(keyword);
    }

    public List<RelationDto> getSearchRelation(String keyword){
        return relationClient.getSearchRelation(keyword);
    }
}
