package com.v1.dgtimes.layer.repository;

/*
설명 : NewsRepository 인터페이스 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    // select * from news n left join keyword_mapping km on n.id = km.news_id
    @Query("select n.id, n.title, n.content, n.main_url, n.thumbnail_url, n.date from News n left join KeywordMapping km where km.news.id = :id")
    List<News> findAllId(@Param("id") Long id);
}


interface newInterface{

}