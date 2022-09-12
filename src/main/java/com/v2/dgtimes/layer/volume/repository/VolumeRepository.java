package com.v2.dgtimes.layer.volume.repository;

import com.v2.dgtimes.layer.news.model.News;
import com.v2.dgtimes.layer.volume.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolumeRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT DATE_FORMAT(published_date,'%Y-%m-%d') as date, " +
            "COUNT(*) as count " +
            "FROM news n " +
            "where MATCH(n.title, n.content) AGAINST(:keyword IN boolean mode) " +
            "and published_date >= date_add(now(), INTERVAL -8 DAY) \n" +
            "and published_date < date_add(now(), INTERVAL -1 DAY)  " +
            "GROUP BY date \n" +
            "ORDER BY date", nativeQuery = true)
    List<Volume> findAllByNewsKeyword(String keyword);

    @Query(value = "SELECT DATE_FORMAT(s.timestamp ,'%Y-%m-%d') date, " +
            "COUNT(*) as count " +
            "FROM search_log s " +
            "where  s.keyword = :keyword " +
            "and s.timestamp >= date_add(now() , INTERVAL -6 DAY) " +
            "GROUP BY date", nativeQuery = true)
    List<Volume> findAllByLogKeyword(String keyword);
}
