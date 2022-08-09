package com.v1.dgtimes.layer.repository;

/*
설명 : BlackKeywordRespository 인터페이스 구현
    > 2022.08.08 인터페이스명 변경 BlackKeywordRespository -> BlackKeywordRepository

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.BlackKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackKeywordRepository extends JpaRepository<BlackKeyword, Long> {
    Long countByBlackKeyword(String black_keyword);
}
