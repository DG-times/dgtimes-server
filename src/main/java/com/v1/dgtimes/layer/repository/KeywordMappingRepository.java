package com.v1.dgtimes.layer.repository;

import com.v1.dgtimes.layer.model.Keyword_mapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordMappingRepository extends JpaRepository<Keyword_mapping, Long> {
    List<Keyword_mapping> findAllByKeywordId(Long keywordId);
}
