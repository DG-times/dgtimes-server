package com.v2.dgtimes.layer.relation.controller;

import com.v2.dgtimes.layer.relation.model.RelationDto;
import com.v2.dgtimes.layer.relation.service.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RelationController {

    private final RelationService relationService;

    @GetMapping("/api/keyword/relation/mention")
    public ResponseEntity<List<RelationDto>> getMentionRelation(
            @RequestParam String keyword
    ){
        return new ResponseEntity(relationService.getMentionRelation(keyword), HttpStatus.OK);
    }

    @GetMapping("/api/keyword/relation/search")
    public ResponseEntity<List<RelationDto>> getSearchRelation(
            @RequestParam String keyword
    ){
        return new ResponseEntity(relationService.getSearchRelation(keyword), HttpStatus.OK);
    }
}
