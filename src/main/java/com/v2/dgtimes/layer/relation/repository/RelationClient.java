package com.v2.dgtimes.layer.relation.repository;

import com.v2.dgtimes.layer.relation.model.RelationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RelationClient {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${relation-server-url}")
    private String relationServerUrl;

    public List<RelationDto> getMentionRelation(String keyword){

        ResponseEntity<List> response = restTemplate.getForEntity(
                relationServerUrl + "/api/keyword/relation/mention?keyword="+keyword,
                List.class);

        return (List<RelationDto>) response.getBody().stream()
                .map(x -> new RelationDto((LinkedHashMap) x))
                .collect(Collectors.toList());
    }

    public List<RelationDto> getSearchRelation(String keyword){

        ResponseEntity<List> response = restTemplate.getForEntity(
                relationServerUrl + "/api/keyword/relation/search?keyword="+keyword,
                List.class);

        return (List<RelationDto>) response.getBody().stream()
                .map(x -> new RelationDto((LinkedHashMap) x))
                .collect(Collectors.toList());
    }

}
