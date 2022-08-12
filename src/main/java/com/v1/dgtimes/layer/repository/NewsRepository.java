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
//    @Query("select n.id, n.title, n.content, n.main_url, n.thumbnail_url, n.date from News n left join KeywordMapping km where km.news.id = :id")
    //select 안에 km.news_id와 같이 특정값을 불러올려고 할때, news 엔티티 안에 없으므로, interface를 통해 매핑시켜줘야한다.
    @Query(value="select * "+
            "from news n "+
            "left join keyword_mapping km on n.id = km.news_id "+
            "where km.KEYWORD_ID = :id", nativeQuery = true)
    List<News> findAllId(@Param("id") Long id);

    @Query(value = "select * "+
            "from news n news "+
            "where like %:keyword% "+
            "in TITLE, CONTEN", nativeQuery = true)
    List<News> findAllByTitleAndContent(@Param("keyword") String keyword);
    // news WHERE LIKE %keyword% IN TITLE, CONTENT
}