package com.v2.dgtimes.layer.news.repository;

import com.v2.dgtimes.layer.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Long> {

    //    Oracle 문법으로 사용 - 참고 : https://blog.naver.com/PostView.nhn?blogId=regenesis90&logNo=222190687396
//    select * from news n, keyword k, keyword_mapping km
//    where n.id = km.news_id and k.id = km.keyword_id and k.keyword like '%코딩%'
//    이걸사용하면, Service쪽에 코드가 짧아질것으로 예상
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

//  이런 방법도 있음
//    select * from news n where n.id in
//            (select news_id from keyword_mapping km where km.keyword_id in
//                    (select id from keyword where keyword like '%코딩%')
//)
    // 이 방법은 select문을 여러번 사용하는거 같아 위의 코드보다 속도가 안나올꺼 같다는 생각
    // Where in (서브쿼리) 보다는 join을 활용할것 - 참고 : https://jojoldu.tistory.com/520
//    @Query(value = "select * from news n where n.id in " +
//            "(select news_id from keyword_mapping km where km.keyword_id in" +
//            "(select id from keyword where keyword like %:keyword%)" +
//            ")", nativeQuery = true)
//    List<News> findAllByKeyword(@Param("keyword") String keyword);

    // 뉴스 엔티티에서 바로 검색 기능
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
    //     news WHERE LIKE %keyword% IN TITLE, CONTENT


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
