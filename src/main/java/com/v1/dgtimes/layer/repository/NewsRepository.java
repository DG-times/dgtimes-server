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

//    Oracle 문법으로 사용 - 참고 : https://blog.naver.com/PostView.nhn?blogId=regenesis90&logNo=222190687396
//    select * from news n, keyword k, keyword_mapping km
//    where n.id = km.news_id and k.id = km.keyword_id and k.keyword like '%코딩%'
//    이걸사용하면, Service쪽에 코드가 짧아질것으로 예상
    @Query(value = "select * "+
            "from news n , keyword k, keyword_mapping km "+
            "where n.id = km.news_id "+
            "and k.id = km.keyword_id "+
            "and k.keyword like %:keyword%", nativeQuery = true)
    List<News> findAllByKeyword(@Param("keyword") String keyword);

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
            "or content like %:keyword%", nativeQuery = true)
    List<News> findAllByTitleAndContent(@Param("keyword") String keyword);
    //     news WHERE LIKE %keyword% IN TITLE, CONTENT
}