package com.v1.dgtimes.layer.repository;

/*
설명 : BookmarkRepository 수정현햇습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookmarkRepository extends JpaRepository<Bookmark, String> {

   boolean existsByKeyword(String keyword);

}
