package com.v2.dgtimes.layer.volume.repository;

import com.v2.dgtimes.layer.volume.model.NewsVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
설명 : NewsVolumeRepository Service입니다.
    - 데이터 측정 범위를 한달에서 일주일로 변경했습니다.

작성일 : 2022.09.11

마지막 수정한 사람 : 안상록

*/
public interface NewsVolumeRepository extends JpaRepository<NewsVolume, String> {

    @Query(value = "SELECT :keyword keyword, DATE_FORMAT(published_date,'%Y-%m-%d') date, " +
            "COUNT(*) as count " +
            "FROM news n " +
            "where MATCH(n.title, n.content) AGAINST(:keyword IN boolean mode) " +
            "and published_date >= date_add(now(), INTERVAL -6 DAY) " +
            "GROUP BY date", nativeQuery = true)
    List<NewsVolume> findAllByKeyword(String keyword);
}
