package com.v2.dgtimes.layer.keyword.repository.source;

import com.v2.dgtimes.layer.keyword.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : 키워드 Main Repositoiry입니다

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
public interface KeywordSourceRepository extends JpaRepository<Keyword, Long> {
}
