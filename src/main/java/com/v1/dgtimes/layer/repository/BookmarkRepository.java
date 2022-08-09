package com.v1.dgtimes.layer.repository;

/*
설명 : BookmarkRepository 구현햇습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookmarkRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByKeyword(String keyword);

}
