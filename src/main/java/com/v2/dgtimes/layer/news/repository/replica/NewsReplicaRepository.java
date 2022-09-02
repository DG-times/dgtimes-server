package com.v2.dgtimes.layer.news.repository.replica;

import com.v2.dgtimes.layer.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
설명 : 뉴스 읽기 전용 Repositoiry입니다

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
public interface NewsReplicaRepository extends JpaRepository<News, Long> {
    @Query(
            value = "select * "+
                    "from news n , keyword k, keyword_mapping km "+
                    "where n.id = km.news_id "+
                    "and k.id = km.keyword_id "+
                    "and k.keyword = :keyword",
            countQuery = "select COUNT(*) "+
                    "from news n , keyword k, keyword_mapping km "+
                    "where n.id = km.news_id "+
                    "and k.id = km.keyword_id "+
                    "and k.keyword = :keyword",
            nativeQuery = true)
    Page<News> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select * "+
            "from news "+
            "where title like %:keyword% "+
            "or content like %:keyword%",
            countQuery = "select COUNT(*) "+
                    "from news "+
                    "where title like %:keyword% "+
                    "or content like %:keyword%",
            nativeQuery = true)
    Page<News> findAllByTitleAndContent(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM news WHERE MATCH(content) AGAINST(:keyword IN boolean mode)",
            countQuery = "SELECT \n" +
                    "            TABLE_ROWS\n" +
                    "FROM\n" +
                    "            information_schema.\n" +
                    "tables\n" +
                    "WHERE table_schema =  'dgtimes' AND TABLE_NAME = \"news\"",
            nativeQuery = true)
    Page<News> findAllByMatch(@Param("keyword") String keyword, Pageable pageable);
}
