package com.v1.dgtimes.layer.repository;

import com.v1.dgtimes.layer.model.KeywordMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordMappingRepository extends JpaRepository<KeywordMapping, Long> {
    List<KeywordMapping> findAllByKeywordId(Long keywordId);
}
