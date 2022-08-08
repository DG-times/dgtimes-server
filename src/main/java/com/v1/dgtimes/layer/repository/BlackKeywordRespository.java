package com.v1.dgtimes.layer.repository;

/*
설명 : BlackKeywordRespository 인터페이스 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.Black_keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackKeywordRespository extends JpaRepository<Black_keyword, Long> {
    Long countByBlackKeyword(String black_keyword);
}
