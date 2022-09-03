package com.v2.dgtimes.layer.keyword.repository;

import com.v2.dgtimes.layer.keyword.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
