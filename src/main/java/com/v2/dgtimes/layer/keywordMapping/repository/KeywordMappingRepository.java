package com.v2.dgtimes.layer.keywordMapping.repository;

import com.v2.dgtimes.layer.keywordMapping.model.KeywordMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordMappingRepository extends JpaRepository<KeywordMapping, Long> {
}
